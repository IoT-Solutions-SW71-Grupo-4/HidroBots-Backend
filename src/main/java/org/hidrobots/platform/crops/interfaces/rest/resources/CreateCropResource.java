package org.hidrobots.platform.crops.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hidrobots.platform.crops.domain.model.valueobjects.IrrigationType;

import java.time.LocalDate;


public record CreateCropResource(
        @NotBlank(message = "Crop name is mandatory")
        String cropName,

        @NotNull(message = "irrigationType is required")
        IrrigationType irrigationType,

        @NotNull(message = "Area is mandatory")
        Long area,

        @NotNull(message = "Planting date is mandatory")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate plantingDate,

        @NotNull(message = "Farmer id is mandatory")
        Long farmerId
) {
}
