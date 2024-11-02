package org.hidrobots.platform.profiles.infrastructure.persistence.jpa.repositories;

import org.hidrobots.platform.profiles.domain.model.entities.FarmerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmerImageRepository extends JpaRepository<FarmerImage, Long> {
}
