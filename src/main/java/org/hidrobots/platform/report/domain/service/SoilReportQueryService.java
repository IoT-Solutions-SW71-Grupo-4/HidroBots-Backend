package org.hidrobots.platform.report.domain.service;

import org.hidrobots.platform.report.domain.model.entities.SoilReport;

import java.util.List;
import java.util.Optional;

public interface SoilReportQueryService {
    List<SoilReport> getAllReport();
    Optional<SoilReport> getReportById(Long id);
    Optional<SoilReport> getLastReport();

    List<SoilReport> getAllReportByCropId(Long cropId);
    Optional<SoilReport> getLastReportByCropId(Long cropId);
}
