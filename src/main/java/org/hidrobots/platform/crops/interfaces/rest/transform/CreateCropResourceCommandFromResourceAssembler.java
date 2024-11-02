package org.hidrobots.platform.crops.interfaces.rest.transform;

import org.hidrobots.platform.crops.domain.model.commands.CreateCropCommand;
import org.hidrobots.platform.crops.interfaces.rest.resources.CreateCropResource;

public class CreateCropResourceCommandFromResourceAssembler {
    public static CreateCropCommand toCommandFromResource(CreateCropResource resource) {
        return new CreateCropCommand(
                resource.cropName(),
                resource.irrigationType(),
                resource.area(),
                resource.plantingDate(),
                resource.farmerId()

        );
    }
}
