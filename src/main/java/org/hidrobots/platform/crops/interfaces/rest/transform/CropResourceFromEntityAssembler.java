package org.hidrobots.platform.crops.interfaces.rest.transform;

import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.hidrobots.platform.crops.interfaces.rest.resources.CropResource;

public class CropResourceFromEntityAssembler {
    public static CropResource toResourceFromEntity(Crop crop) {
        return new CropResource(
                crop.getId(),
                crop.getCropName(),
                crop.getIrrigationType().name(),
                crop.getArea(),
                crop.getPlantingDate().toString(),
                crop.getFarmerId(),
                crop.getCropImage() != null ? crop.getCropImage().getImageUrl() : null // Aquí evitamos el NullPointerException
        );
    }

}