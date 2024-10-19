package org.hidrobots.platform.crops.domain.model.commands;

import org.hidrobots.platform.crops.domain.model.valueobjects.IrrigationType;

import java.time.LocalDate;
import java.util.Date;

public record CreateCropCommand(
        String cropName,
        IrrigationType irrigationType,
        Long area,
        LocalDate plantingDate,
        Long farmerId
) {
}
