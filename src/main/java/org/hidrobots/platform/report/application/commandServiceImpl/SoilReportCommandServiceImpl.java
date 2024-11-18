package org.hidrobots.platform.report.application.commandServiceImpl;

import jakarta.transaction.Transactional;
import org.hidrobots.platform.devices.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.hidrobots.platform.report.domain.model.commands.CreateSoilReportCommand;
import org.hidrobots.platform.report.domain.model.commands.CreateSoilReportWithDeviceCodeCommand;
import org.hidrobots.platform.report.domain.model.entities.SoilReport;
import org.hidrobots.platform.report.domain.service.SoilReportCommandService;
import org.hidrobots.platform.report.infrastructure.repositories.SoilReportRepository;
import org.springframework.stereotype.Service;

@Service
public class SoilReportCommandServiceImpl implements SoilReportCommandService {
    private final SoilReportRepository soilReportRepository;
    private final DeviceRepository deviceRepository;

    public SoilReportCommandServiceImpl(SoilReportRepository soilReportRepository, DeviceRepository deviceRepository) {
        this.soilReportRepository = soilReportRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    @Transactional
    public Long createSoilReport(CreateSoilReportCommand command) {
        if (!deviceRepository.existsById(command.deviceId())) {
            throw new IllegalArgumentException("Device with id " + command.deviceId() + " doesn't exist!!");
        }
        SoilReport soilReport = new SoilReport(command);
        return soilReportRepository.save(soilReport).getId();
    }

    @Override
    public Long createSoilReport(CreateSoilReportWithDeviceCodeCommand command) {
        var existingDevice = deviceRepository.findByDeviceCode(command.getDeviceCode());
        if (existingDevice.isEmpty()) {
            throw new IllegalArgumentException("Device with code " + command.getDeviceCode() + " doesn't exist!!");
        }
        SoilReport soilReport = new SoilReport(command, existingDevice.get().getId());
        return soilReportRepository.save(soilReport).getId();
    }

}
