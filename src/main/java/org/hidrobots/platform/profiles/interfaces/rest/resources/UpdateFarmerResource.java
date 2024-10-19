package org.hidrobots.platform.profiles.interfaces.rest.resources;

public record UpdateFarmerResource(
        String username,
        String email,
        String phoneNumber,
        String password
) {
}
