package org.hidrobots.platform.crops.domain.model.commands;

import org.hidrobots.platform.crops.domain.model.valueobjects.IrrigationType;

public record UpdateIrrigationTypeCommand(
        Long id,
        IrrigationType irrigationType
) {
}
