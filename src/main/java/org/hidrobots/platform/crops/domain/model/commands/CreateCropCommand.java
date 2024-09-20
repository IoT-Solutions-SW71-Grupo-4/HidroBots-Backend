package org.hidrobots.platform.crops.domain.model.commands;

public record CreateCropCommand(
        String cropName,
        String cropDescription
) {
}
