package org.hidrobots.platform.crops.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record UpdateCropNameResource(
        @NotBlank(message = "Crop name is mandatory")
        String cropName
) {
}
