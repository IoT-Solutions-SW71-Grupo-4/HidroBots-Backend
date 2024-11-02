package org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories;

import org.hidrobots.platform.crops.domain.model.entities.FarmerRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRegistryRepository extends JpaRepository<FarmerRegistry, Long> {
}
