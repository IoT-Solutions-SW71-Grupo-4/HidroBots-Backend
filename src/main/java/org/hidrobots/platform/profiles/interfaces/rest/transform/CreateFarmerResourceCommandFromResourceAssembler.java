package org.hidrobots.platform.profiles.interfaces.rest.transform;

import org.hidrobots.platform.profiles.domain.model.commands.CreateFarmerCommand;
import org.hidrobots.platform.profiles.interfaces.rest.resources.CreateFarmerResource;

public class CreateFarmerResourceCommandFromResourceAssembler {
    public static CreateFarmerCommand toCommandFromResource(CreateFarmerResource resource) {
        return new CreateFarmerCommand(
                resource.username(),
                resource.email(),
                resource.phoneNumber(),
                resource.password()
        );
    }
}
