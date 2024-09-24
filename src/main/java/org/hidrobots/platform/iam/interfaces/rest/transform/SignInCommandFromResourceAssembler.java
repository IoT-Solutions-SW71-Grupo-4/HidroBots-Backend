package org.hidrobots.platform.iam.interfaces.rest.transform;


import org.hidrobots.platform.iam.domain.model.commands.SignInCommand;
import org.hidrobots.platform.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }

}
