package org.hidrobots.platform.report.interfaces.resources;

public record CreateWeatherReportResource(
        String temperature,
        String humidity,
        Long deviceId
) {
}
