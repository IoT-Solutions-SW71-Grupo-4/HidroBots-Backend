package org.hidrobots.platform.profiles.application.internal.eventHandlers.eventListeners;

import org.hidrobots.platform.iam.domain.model.events.UserRegisteredEvent;
import org.hidrobots.platform.profiles.domain.model.aggregates.Farmer;
import org.hidrobots.platform.profiles.infrastructure.persistence.jpa.repositories.FarmerRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserRegisteredEventHandler {

    private final FarmerRepository farmerRepository;

    public UserRegisteredEventHandler(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {

        String email = event.getEmail();
        String fullName = event.getFullName();
        String password = event.getPassword();

        Farmer farmer = new Farmer(fullName, email, "", password);
        farmerRepository.save(farmer);

        System.out.println("Creating profile to user with email: " + email + " and name: " + fullName);
    }
}
