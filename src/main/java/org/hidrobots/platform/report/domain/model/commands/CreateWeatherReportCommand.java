package org.hidrobots.platform.report.domain.model.commands;

import org.hidrobots.platform.crops.domain.model.entities.Device;

public record CreateWeatherReportCommand(
        Double temperature,
        Double humidity,
        Long deviceId
) {
}
