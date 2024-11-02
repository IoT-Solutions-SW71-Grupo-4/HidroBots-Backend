package org.hidrobots.platform.crops.domain.model.commands;

import org.hidrobots.platform.crops.domain.model.entities.CropImage;
import org.hidrobots.platform.crops.domain.model.valueobjects.IrrigationType;

import java.time.LocalDate;

public record UpdateCropCommand(
        Long id,
        String cropName,
        IrrigationType irrigationType,
        Long area, LocalDate plantingDate,
        Long farmerId
) {
}
