package org.hidrobots.platform.report.interfaces.resources;

public record CreateSoilReportResource(
        Double nitrogen,
        Double phosphorus,
        Double potassium,
        Long deviceId
) {
}
