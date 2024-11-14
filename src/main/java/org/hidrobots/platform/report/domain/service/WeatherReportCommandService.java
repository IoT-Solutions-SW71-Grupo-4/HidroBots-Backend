package org.hidrobots.platform.report.domain.service;

import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportCommand;

public interface WeatherReportCommandService {
    Long createWeatherReport(CreateWeatherReportCommand command);
}
