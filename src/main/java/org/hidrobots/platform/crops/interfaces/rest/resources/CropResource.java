package org.hidrobots.platform.crops.interfaces.rest.resources;

public record CropResource(
        Long id,
        String cropName,
        String irrigationType,
        Long area,
        String plantingDate,
        Long farmerId,
        String imageUrl
) {
}
