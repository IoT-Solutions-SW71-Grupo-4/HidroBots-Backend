package org.hidrobots.platform.report.application.queryServiceImpl;

import org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.hidrobots.platform.devices.domain.model.entities.Device;
import org.hidrobots.platform.devices.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.hidrobots.platform.report.domain.model.entities.WeatherReport;
import org.hidrobots.platform.report.domain.service.WeatherReportQueryService;
import org.hidrobots.platform.report.infrastructure.repositories.WeatherReportRepository;
import org.hidrobots.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherReportQueryServiceImpl  implements WeatherReportQueryService {

    private final WeatherReportRepository weatherReportRepository;
    private final CropRepository cropRepository;
    private final DeviceRepository deviceRepository;

    public WeatherReportQueryServiceImpl(WeatherReportRepository weatherReportRepository, CropRepository cropRepository, DeviceRepository deviceRepository) {
        this.weatherReportRepository = weatherReportRepository;
        this.cropRepository = cropRepository;
        this.deviceRepository = deviceRepository;
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

    @Override
    public List<WeatherReport> getAllReportByCropId(Long cropId) {
        var device = deviceRepository.findByCropId(cropId);
        List<Long> deviceIds = device.stream().map(Device::getId).toList();
        return weatherReportRepository.findAllByDeviceIdIn(deviceIds);
    }

    @Override
    public Optional<WeatherReport> getLastReportByCropId(Long cropId) {
        var device = deviceRepository.findByCropId(cropId);
        List<Long> deviceIds = device.stream().map(Device::getId).toList();
        return weatherReportRepository.findTopByDeviceIdInOrderByDateTimeDesc(deviceIds);
    }
}
