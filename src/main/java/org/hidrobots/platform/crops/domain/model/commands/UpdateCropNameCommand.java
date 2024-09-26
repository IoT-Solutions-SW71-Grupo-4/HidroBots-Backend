package org.hidrobots.platform.crops.domain.model.commands;

public record UpdateCropNameCommand(
        Long id,
        String cropName
) {
}
