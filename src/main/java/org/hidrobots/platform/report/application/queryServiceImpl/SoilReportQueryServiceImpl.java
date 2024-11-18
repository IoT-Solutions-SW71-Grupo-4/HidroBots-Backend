package org.hidrobots.platform.report.application.queryServiceImpl;

import org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.hidrobots.platform.devices.domain.model.entities.Device;
import org.hidrobots.platform.devices.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.hidrobots.platform.report.domain.model.entities.SoilReport;
import org.hidrobots.platform.report.domain.service.SoilReportQueryService;
import org.hidrobots.platform.report.infrastructure.repositories.SoilReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoilReportQueryServiceImpl implements SoilReportQueryService {

    private final SoilReportRepository soilReportRepository;

    private final DeviceRepository deviceRepository;
    public SoilReportQueryServiceImpl(SoilReportRepository soilReportRepository, DeviceRepository deviceRepository) {
        this.soilReportRepository = soilReportRepository;
        this.deviceRepository = deviceRepository;
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

    @Override
    public List<SoilReport> getAllReportByCropId(Long cropId) {
        var device = deviceRepository.findByCropId(cropId);
        List<Long> deviceIds = device.stream().map(Device::getId).toList();
        return soilReportRepository.findAllByDeviceIdIn(deviceIds);
    }

    @Override
    public Optional<SoilReport> getLastReportByCropId(Long cropId) {
        var device = deviceRepository.findByCropId(cropId);
        List<Long> deviceIds = device.stream().map(Device::getId).toList();
        return soilReportRepository.findTopByDeviceIdInOrderByDateTimeDesc(deviceIds);
    }
}
