package org.hidrobots.platform.report.infrastructure.repositories;

import org.hidrobots.platform.devices.domain.model.entities.Device;
import org.hidrobots.platform.report.domain.model.entities.SoilReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SoilReportRepository extends JpaRepository<SoilReport, Long> {
    Optional<SoilReport> findTopByOrderByDateTimeDesc();

    List<SoilReport> findAllByDeviceIdIn(List<Long> deviceIds);

    Optional<SoilReport> findTopByDeviceIdInOrderByDateTimeDesc(List<Long> deviceIds);
}
