package org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories;

import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    boolean existsCropsByCropName(String name);
}