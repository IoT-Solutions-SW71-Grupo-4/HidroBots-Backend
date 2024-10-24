package org.hidrobots.platform.profiles.domain.services;

import org.hidrobots.platform.profiles.domain.model.aggregates.Farmer;
import org.hidrobots.platform.profiles.domain.model.commands.CreateFarmerCommand;
import org.hidrobots.platform.profiles.domain.model.commands.UpdateFarmerCommand;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface FarmerCommandService {

    Long createFarmer(CreateFarmerCommand command);
    Optional<Farmer> updateFarmer(UpdateFarmerCommand command);
    void deleteFarmer(Long farmerId);

    Optional<Farmer> UpdateFarmerImage(MultipartFile file, Farmer farmer) throws IOException;
    Optional<Farmer> deleteFarmerImage(Long farmerId) throws IOException;
}
