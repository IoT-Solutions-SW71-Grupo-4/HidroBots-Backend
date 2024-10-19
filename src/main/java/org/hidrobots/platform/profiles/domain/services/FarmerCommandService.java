package org.hidrobots.platform.profiles.domain.services;

import org.hidrobots.platform.profiles.domain.model.aggregates.Farmer;
import org.hidrobots.platform.profiles.domain.model.commands.CreateFarmerCommand;
import org.hidrobots.platform.profiles.domain.model.commands.UpdateFarmerCommand;

import java.util.Optional;

public interface FarmerCommandService {

    Long createFarmer(CreateFarmerCommand command);
    Optional<Farmer> updateFarmer(UpdateFarmerCommand command);
    void deleteFarmer(Long farmerId);
}
