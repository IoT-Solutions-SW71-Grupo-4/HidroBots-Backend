package org.hidrobots.platform.crops.interfaces.rest.transform;

import org.hidrobots.platform.crops.domain.model.commands.UpdateCropNameCommand;
import org.hidrobots.platform.crops.interfaces.rest.resources.UpdateCropNameResource;

public class UpdateCropNameResourceCommandFromResourceAssembler {
    public static UpdateCropNameCommand toCommandFromResource(Long id, UpdateCropNameResource resource) {
        return new UpdateCropNameCommand(
                id,
                resource.cropName()
        );
    }
}
