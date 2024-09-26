package org.hidrobots.platform.iam.application.internal.commandServices;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.hidrobots.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.hidrobots.platform.iam.application.internal.outboundservices.tokens.TokenService;
import org.hidrobots.platform.iam.domain.model.aggregates.User;
import org.hidrobots.platform.iam.domain.model.commands.SignInCommand;
import org.hidrobots.platform.iam.domain.model.commands.SignUpCommand;
import org.hidrobots.platform.iam.domain.model.valueobjects.Roles;
import org.hidrobots.platform.iam.domain.services.UserCommandService;
import org.hidrobots.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.hidrobots.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }



    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email()))
            throw new RuntimeException("Username already exists");

        var roles = command.roles();
        if (roles.isEmpty()) {
            var role = roleRepository.findByName(Roles.ROLE_USER);
            if (role.isPresent()) roles.add(role.get());
        } else roles = roles.stream().map(role -> roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Role not found"))).toList();
            var user = new User(command.fullName(), command.email(), hashingService.encode(command.password()), roles);
            userRepository.save(user);
        return userRepository.findByEmail(command.email());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!hashingService.matches(command.password(), user.getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.getEmail());
        return Optional.of(new ImmutablePair<>(user, token));
    }

}
