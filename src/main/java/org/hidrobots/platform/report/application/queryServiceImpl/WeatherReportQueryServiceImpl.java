package org.hidrobots.platform.report.application.queryServiceImpl;

import org.hidrobots.platform.report.domain.model.entities.WeatherReport;
import org.hidrobots.platform.report.domain.service.WeatherReportQueryService;
import org.hidrobots.platform.report.infrastructure.repositories.WeatherReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherReportQueryServiceImpl  implements WeatherReportQueryService {

    private final WeatherReportRepository weatherReportRepository;

    public WeatherReportQueryServiceImpl(WeatherReportRepository weatherReportRepository) {
        this.weatherReportRepository = weatherReportRepository;
    }

    @Override
    public List<WeatherReport> getAllReport() {
        return weatherReportRepository.findAll();
    }

    @Override
    public Optional<WeatherReport> getReportById(Long id) {
        return weatherReportRepository.findById(id);
    }

    @Override
    public Optional<WeatherReport> getLastReport() {
        return weatherReportRepository.findTopByOrderByDateTimeDesc();
    }
}
