package org.hidrobots.platform.report.application.queryServiceImpl;

import org.hidrobots.platform.report.domain.model.entities.SoilReport;
import org.hidrobots.platform.report.domain.service.SoilReportQueryService;
import org.hidrobots.platform.report.infrastructure.repositories.SoilReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoilReportQueryServiceImpl implements SoilReportQueryService {

    private final SoilReportRepository soilReportRepository;

    public SoilReportQueryServiceImpl(SoilReportRepository soilReportRepository) {
        this.soilReportRepository = soilReportRepository;
    }

    @Override
    public List<SoilReport> getAllReport() {
        return soilReportRepository.findAll();
    }

    @Override
    public Optional<SoilReport> getReportById(Long id) {
        return soilReportRepository.findById(id);
    }

    @Override
    public Optional<SoilReport> getLastReport() {
        return soilReportRepository.findTopByOrderByDateTimeDesc();
    }
}
