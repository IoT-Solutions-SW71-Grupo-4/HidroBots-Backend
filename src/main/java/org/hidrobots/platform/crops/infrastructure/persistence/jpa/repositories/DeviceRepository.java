package org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories;


import org.hidrobots.platform.crops.domain.model.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
