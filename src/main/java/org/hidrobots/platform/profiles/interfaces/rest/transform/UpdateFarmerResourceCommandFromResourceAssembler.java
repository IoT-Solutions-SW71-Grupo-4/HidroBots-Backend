package org.hidrobots.platform.profiles.interfaces.rest.transform;

import org.hidrobots.platform.profiles.domain.model.commands.UpdateFarmerCommand;
import org.hidrobots.platform.profiles.interfaces.rest.resources.UpdateFarmerResource;

// se actualiza solo el username y el phoneNumber (de acuerdo a los atributos del command)
public class UpdateFarmerResourceCommandFromResourceAssembler {
    public static UpdateFarmerCommand toCommandFromResource(UpdateFarmerResource resource, Long farmerId) {
        return new UpdateFarmerCommand(
                farmerId,
                resource.username(),
                resource.phoneNumber()
        );
    }
}
