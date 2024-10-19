package org.hidrobots.platform.iam.cucumber.signIn;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.hidrobots.platform.iam.application.internal.commandServices.UserCommandServiceImpl;
import org.hidrobots.platform.iam.domain.model.commands.SignInCommand;
import org.hidrobots.platform.iam.domain.model.aggregates.User;
import org.hidrobots.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.hidrobots.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.hidrobots.platform.iam.application.internal.outboundservices.tokens.TokenService;
import org.mockito.Mockito;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignInCommandServiceImplTest {

    private UserCommandServiceImpl userCommandService;
    private SignInCommand signInCommand;
    private Optional<ImmutablePair<User, String>> signInResult;

    @Given("an existing user with email {string} and password {string}")
    public void an_existing_user_with_email_and_password(String email, String password) {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        HashingService hashingService = Mockito.mock(HashingService.class);
        TokenService tokenService = Mockito.mock(TokenService.class);

        User existingUser = new User("Existing User", email, "encodedPassword", new ArrayList<>());
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
        Mockito.when(hashingService.matches(password, "encodedPassword")).thenReturn(true);
        Mockito.when(tokenService.generateToken(email)).thenReturn("mockToken");

        userCommandService = new UserCommandServiceImpl(userRepository, null, hashingService, tokenService, null);
        signInCommand = new SignInCommand(email, password);
    }

    @When("the user attempts to sign in")
    public void the_user_attempts_to_sign_in() {
        signInResult = userCommandService.handle(signInCommand);
    }

    @Then("the sign-in should be successful")
    public void the_sign_in_should_be_successful() {
        assertTrue(signInResult.isPresent(), "The sign-in should be successful, but it was not.");
    }
}