package org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories;

import org.hidrobots.platform.crops.domain.model.entities.CropImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropImageRepository extends JpaRepository<CropImage, Long> {
}
