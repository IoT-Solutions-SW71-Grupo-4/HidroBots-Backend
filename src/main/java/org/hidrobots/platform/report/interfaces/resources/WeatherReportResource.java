package org.hidrobots.platform.report.interfaces.resources;

import java.util.Date;

public record WeatherReportResource(
        Long id,
        Date date,
        String temperature,
        String humidity,
        Long deviceId
) {
}
