package org.hidrobots.platform.profiles.application.internal.commandServiceImpl;

import org.hidrobots.platform.profiles.domain.model.aggregates.Farmer;
import org.hidrobots.platform.profiles.domain.model.commands.CreateFarmerCommand;
import org.hidrobots.platform.profiles.domain.model.commands.UpdateFarmerCommand;
import org.hidrobots.platform.profiles.domain.services.FarmerCommandService;
import org.hidrobots.platform.profiles.infrastructure.persistence.jpa.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FarmerCommandServiceImpl implements FarmerCommandService {

    private final FarmerRepository farmerRepository;

    @Autowired
    public FarmerCommandServiceImpl(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @Override
    public Long createFarmer(CreateFarmerCommand command) {

        Farmer farmer = new Farmer(
                command.username(),
                command.password(),
                command.email(),
                command.phoneNumber()
        );
        farmerRepository.save(farmer);
        return farmer.getId();
    }

    @Override
    public Optional<Farmer> updateFarmer(UpdateFarmerCommand command) {
        var farmer = farmerRepository.findById(command.farmerId()).orElseThrow(() -> new IllegalArgumentException("Farmer with id " + command.farmerId() + "doesn't exist!!"));

        if (command.username() != null && !command.username().isEmpty()) {
            farmer.setUsername(command.username());
        }

        if (command.phoneNumber() != null && !command.phoneNumber().isEmpty()) {
            farmer.setPhoneNumber(command.phoneNumber());
        }

        return Optional.of(farmer);

    }

    @Override
    public void deleteFarmer(Long farmerId) {
        if (!farmerRepository.existsById(farmerId)) {
            throw new IllegalArgumentException("Farmer with id " + farmerId + "doesn't exist!!");
        }
        try {
            farmerRepository.deleteById(farmerId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting farmer: " + e.getMessage());
        }
    }
}
