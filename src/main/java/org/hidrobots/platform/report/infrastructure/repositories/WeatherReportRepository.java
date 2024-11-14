package org.hidrobots.platform.report.infrastructure.repositories;

import org.hidrobots.platform.report.domain.model.entities.WeatherReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherReportRepository extends JpaRepository<WeatherReport, Long> {
    Optional<WeatherReport> findTopByOrderByDateTimeDesc();
}
