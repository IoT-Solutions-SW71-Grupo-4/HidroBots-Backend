package org.hidrobots.platform.report.domain.service;

import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportCommand;
import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportWithDeviceCodeCommand;

public interface WeatherReportCommandService {
    Long createWeatherReport(CreateWeatherReportCommand command);
    Long createWeatherReport(CreateWeatherReportWithDeviceCodeCommand command);
}
