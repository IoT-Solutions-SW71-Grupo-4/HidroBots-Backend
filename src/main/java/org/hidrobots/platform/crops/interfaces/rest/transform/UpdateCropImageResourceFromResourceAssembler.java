package org.hidrobots.platform.crops.interfaces.rest.transform;

import org.hidrobots.platform.crops.domain.model.commands.UpdateCropImageCommand;
import org.hidrobots.platform.crops.interfaces.rest.resources.UpdateCropImageResource;

public class UpdateCropImageResourceFromResourceAssembler {
    public static UpdateCropImageCommand toCommandFromResource(Long id, UpdateCropImageResource resource) {
        return new UpdateCropImageCommand(
                id,
                resource.cropImage()
        );
    }
}
