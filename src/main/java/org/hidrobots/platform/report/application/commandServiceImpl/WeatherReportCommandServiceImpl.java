package org.hidrobots.platform.report.application.commandServiceImpl;

import jakarta.transaction.Transactional;
import org.hidrobots.platform.devices.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportCommand;
import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportWithDeviceCodeCommand;
import org.hidrobots.platform.report.domain.model.entities.WeatherReport;
import org.hidrobots.platform.report.domain.service.WeatherReportCommandService;
import org.hidrobots.platform.report.infrastructure.repositories.WeatherReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportCommandServiceImpl implements WeatherReportCommandService {

    private final WeatherReportRepository weatherReportRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public WeatherReportCommandServiceImpl(WeatherReportRepository weatherReportRepository, DeviceRepository deviceRepository) {
        this.weatherReportRepository = weatherReportRepository;
        this.deviceRepository = deviceRepository;
    }
    @Override
    @Transactional
    public Long createWeatherReport(CreateWeatherReportCommand command) {
        if (!deviceRepository.existsById(command.deviceId())) {
            throw new IllegalArgumentException("Device with id " + command.deviceId() + " doesn't exist!!");
        }

        WeatherReport weatherReport = new WeatherReport(command);
        return weatherReportRepository.save(weatherReport).getId();
    }

    @Override
    public Long createWeatherReport(CreateWeatherReportWithDeviceCodeCommand command) {
        var existingDevice = deviceRepository.findByDeviceCode(command.getDeviceCode());

        if (existingDevice.isEmpty()) {
            throw new IllegalArgumentException("Device with code " + command.getDeviceCode() + " doesn't exist!!");
        }

        WeatherReport weatherReport = new WeatherReport(command, existingDevice.get().getId());

        return weatherReportRepository.save(weatherReport).getId();
    }
}
