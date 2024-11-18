package org.hidrobots.platform.report.domain.service;

import org.hidrobots.platform.report.domain.model.entities.WeatherReport;

import java.util.List;
import java.util.Optional;

public interface WeatherReportQueryService {
    List<WeatherReport> getAllReport();
    Optional<WeatherReport> getReportById(Long id);
    Optional<WeatherReport> getLastReport();
    List<WeatherReport>  getAllReportByCropId(Long cropId);
    Optional<WeatherReport> getLastReportByCropId(Long cropId);
}
