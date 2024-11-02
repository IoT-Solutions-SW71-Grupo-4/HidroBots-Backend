package org.hidrobots.platform.iam.cucumber.signUp;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.hidrobots.platform.iam.application.internal.commandServices.UserCommandServiceImpl;
import org.hidrobots.platform.iam.domain.model.commands.SignUpCommand;
import org.hidrobots.platform.iam.domain.model.aggregates.User;
import org.hidrobots.platform.iam.domain.model.entities.Role;
import org.hidrobots.platform.iam.domain.model.valueobjects.Roles;
import org.hidrobots.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.hidrobots.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.hidrobots.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.hidrobots.platform.iam.application.internal.outboundservices.tokens.TokenService;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpCommandServiceImplTest {

    private UserCommandServiceImpl userCommandService;
    private SignUpCommand signUpCommand;
    private Optional<User> signUpResult;

    @Given("a new user with fullName {string}, email {string} and password {string}")
    public void a_new_user_with_fullName_email_and_password(String fullName, String email, String password) {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        RoleRepository roleRepository = Mockito.mock(RoleRepository.class);
        HashingService hashingService = Mockito.mock(HashingService.class);
        TokenService tokenService = Mockito.mock(TokenService.class);

        // Mocking the role repository to return a default role
        Role defaultRole = new Role();
        defaultRole.setName(Roles.ROLE_USER);
        Mockito.when(roleRepository.findByName(Roles.ROLE_USER)).thenReturn(Optional.of(defaultRole));

        Mockito.when(userRepository.existsByEmail(email)).thenReturn(false);
        Mockito.when(hashingService.encode(password)).thenReturn("encodedPassword");

        userCommandService = new UserCommandServiceImpl(userRepository, roleRepository, hashingService, tokenService, null);
        signUpCommand = new SignUpCommand(fullName, email, password, new ArrayList<>());

        // Mocking the save and findByEmail methods
        User newUser = new User(fullName, email, "encodedPassword", new ArrayList<>());
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(newUser);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(newUser));
    }

    @When("the user attempts to sign up")
    public void the_user_attempts_to_sign_up() {
        signUpResult = userCommandService.handle(signUpCommand);
    }

    @Then("the sign-up should be successful")
    public void the_sign_up_should_be_successful() {
        assertTrue(signUpResult.isPresent(), "The sign-up should be successful, but it was not.");
    }
}