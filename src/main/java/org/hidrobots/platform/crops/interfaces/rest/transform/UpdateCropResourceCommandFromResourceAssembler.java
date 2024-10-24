package org.hidrobots.platform.crops.interfaces.rest.transform;

import org.hidrobots.platform.crops.domain.model.commands.UpdateCropCommand;
import org.hidrobots.platform.crops.domain.model.entities.CropImage;
import org.hidrobots.platform.crops.interfaces.rest.resources.UpdateCropResource;

public class UpdateCropResourceCommandFromResourceAssembler {
    public static UpdateCropCommand toCommandFromResource(Long id, UpdateCropResource resource) {

        return new UpdateCropCommand(
                id,
                resource.cropName(),
                resource.irrigationType(),
                resource.area(),
                resource.plantingDate(),
                resource.farmerId()
        );
    }
}
