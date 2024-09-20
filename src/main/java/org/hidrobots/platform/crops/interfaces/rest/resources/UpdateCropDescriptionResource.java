package org.hidrobots.platform.crops.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

public record UpdateCropDescriptionResource(
        @NotBlank(message = "Crop description is mandatory")
        String cropDescription
) {
}
