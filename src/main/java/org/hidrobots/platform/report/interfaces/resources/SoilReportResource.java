package org.hidrobots.platform.report.interfaces.resources;

import java.util.Date;

public record SoilReportResource(
        Long id,
        Date date,
        Double nitrogen,
        Double phosphorus,
        Double potassium,
        Long deviceId
) {
}
