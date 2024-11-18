package org.hidrobots.platform.devices.infrastructure.persistence.jpa.repositories;

import org.hidrobots.platform.devices.domain.model.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByCropId(Long cropId);

    @Query("SELECT d FROM Device d WHERE d.cropId IN (SELECT c.id FROM Crop c WHERE c.farmerId = :farmerId)")
    List<Device> findByFarmerId(@Param("farmerId") Long farmerId);

    @Query("SELECT d FROM Device d WHERE d.deviceCode = :deviceCode")
    Optional<Device> findByDeviceCode(String deviceCode);
}
