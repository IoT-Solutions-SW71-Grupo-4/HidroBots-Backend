package org.hidrobots.platform.iam.interfaces.rest.resources;

public record SignInResource(
    String email,
    String password
) {

}
