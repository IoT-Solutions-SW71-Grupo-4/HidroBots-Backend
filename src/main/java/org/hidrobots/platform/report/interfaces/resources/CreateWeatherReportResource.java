package org.hidrobots.platform.report.interfaces.resources;

public record CreateWeatherReportResource(
        Double temperature,
        Double humidity,
        Long deviceId
) {
}
