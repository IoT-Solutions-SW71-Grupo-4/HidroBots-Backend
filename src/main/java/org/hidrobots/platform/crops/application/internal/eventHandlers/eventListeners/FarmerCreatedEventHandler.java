package org.hidrobots.platform.crops.application.internal.eventHandlers.eventListeners;

import org.hidrobots.platform.crops.domain.model.entities.FarmerRegistry;
import org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories.FarmerRegistryRepository;
import org.hidrobots.platform.profiles.domain.model.aggregates.Farmer;
import org.hidrobots.platform.profiles.domain.model.events.FarmerCreatedEvent;
import org.hidrobots.platform.profiles.infrastructure.persistence.jpa.repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class FarmerCreatedEventHandler {

    private final FarmerRegistryRepository farmerRegistryRepository;

    @Autowired
    public FarmerCreatedEventHandler(FarmerRegistryRepository farmerRegistryRepository) {
        this.farmerRegistryRepository = farmerRegistryRepository;
    }

    @EventListener
    public void handleFarmerCreatedEvent(FarmerCreatedEvent event) {

        Long farmerId = event.getFarmerId();

        FarmerRegistry farmerRegistry = new FarmerRegistry(farmerId);
        farmerRegistryRepository.save(farmerRegistry);

        System.out.println("Received FarmerCreatedEvent for farmerId: " + event.getFarmerId());
    }
}
