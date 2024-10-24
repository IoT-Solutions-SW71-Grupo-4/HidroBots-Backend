package org.hidrobots.platform.crops.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import org.hidrobots.platform.crops.domain.model.entities.CropImage;
import org.hidrobots.platform.crops.domain.model.valueobjects.IrrigationType;

import java.time.LocalDate;

public record UpdateCropResource(
        @NotBlank(message = "Crop name is mandatory")
        String cropName,
        IrrigationType irrigationType,
        Long area,
        LocalDate plantingDate,
        Long farmerId
) {
}
