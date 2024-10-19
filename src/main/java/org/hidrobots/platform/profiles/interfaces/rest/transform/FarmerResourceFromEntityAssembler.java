package org.hidrobots.platform.profiles.interfaces.rest.transform;

import org.hidrobots.platform.profiles.domain.model.aggregates.Farmer;
import org.hidrobots.platform.profiles.interfaces.rest.resources.FarmerResource;

public class FarmerResourceFromEntityAssembler {
    public static FarmerResource toResourceFromEntity(Farmer farmer) {
        return new FarmerResource(
                farmer.getId(),
                farmer.getUsername(),
                farmer.getEmail(),
                farmer.getPhoneNumber(),
                farmer.getPassword()
        );
    }
}
