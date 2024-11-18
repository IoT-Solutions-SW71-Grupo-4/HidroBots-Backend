package org.hidrobots.platform.report.domain.model.commands;


public record CreateWeatherReportCommand(
        Double temperature,
        Double humidity,
        Long deviceId
) {
}
