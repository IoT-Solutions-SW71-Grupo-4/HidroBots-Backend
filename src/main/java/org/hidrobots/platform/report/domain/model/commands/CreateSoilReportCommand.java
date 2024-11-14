package org.hidrobots.platform.report.domain.model.commands;

public record CreateSoilReportCommand(
        Double nitrogen,
        Double phosphorus,
        Double potassium,
        Long deviceId
) {
}
