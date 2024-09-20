package org.hidrobots.platform.crops.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record CreateCropResource(
        @NotBlank(message = "Crop name is mandatory")
        String cropName,

        @NotBlank(message = "Crop description is mandatory")
        String cropDescription
) {
}
