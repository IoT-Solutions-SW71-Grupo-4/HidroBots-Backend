package org.hidrobots.platform.crops.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hidrobots.platform.crops.domain.model.valueobjects.IrrigationType;

public record UpdateIrrigationTypeResource(
        @NotNull(message = "irrigationType is required")
        IrrigationType irrigationType
) {
}
