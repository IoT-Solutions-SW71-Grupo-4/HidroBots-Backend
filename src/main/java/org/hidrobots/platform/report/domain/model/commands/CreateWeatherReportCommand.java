package org.hidrobots.platform.report.domain.model.commands;

import org.hidrobots.platform.crops.domain.model.entities.Device;

public record CreateWeatherReportCommand(
        String temperature,
        String humidity,
        Long deviceId
) {
}
