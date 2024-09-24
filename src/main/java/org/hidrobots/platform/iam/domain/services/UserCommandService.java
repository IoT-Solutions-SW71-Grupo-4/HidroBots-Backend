package org.hidrobots.platform.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.hidrobots.platform.iam.domain.model.aggregates.User;
import org.hidrobots.platform.iam.domain.model.commands.SignInCommand;
import org.hidrobots.platform.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;


public interface UserCommandService {

    Optional<User> handle(SignUpCommand command); // comando para registrar un usuario

    Optional<ImmutablePair<User, String>> handle(SignInCommand command); // comando para iniciar sesión, devuelve un par de usuario y token
}