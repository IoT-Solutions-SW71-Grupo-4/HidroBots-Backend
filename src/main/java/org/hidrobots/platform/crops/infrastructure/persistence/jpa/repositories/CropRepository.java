package org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories;

import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    boolean existsCropsByCropName(String name);
    List<Crop> findCropByFarmerId(Long farmerId);
}