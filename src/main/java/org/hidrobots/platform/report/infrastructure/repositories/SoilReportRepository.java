package org.hidrobots.platform.report.infrastructure.repositories;

import org.hidrobots.platform.report.domain.model.entities.SoilReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SoilReportRepository extends JpaRepository<SoilReport, Long> {
    Optional<SoilReport> findTopByOrderByDateTimeDesc();
}
