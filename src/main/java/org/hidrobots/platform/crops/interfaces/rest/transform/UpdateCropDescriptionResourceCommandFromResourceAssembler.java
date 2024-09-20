package org.hidrobots.platform.crops.interfaces.rest.transform;

import org.hidrobots.platform.crops.domain.model.commands.UpdateCropNameCommand;
import org.hidrobots.platform.crops.interfaces.rest.resources.UpdateCropDescriptionResource;

public class UpdateCropDescriptionResourceCommandFromResourceAssembler {
    public static UpdateCropNameCommand toCommandFromResource(Long id, UpdateCropDescriptionResource resource) {
        return new UpdateCropNameCommand(
                id,
                resource.cropDescription()
        );
    }
}
