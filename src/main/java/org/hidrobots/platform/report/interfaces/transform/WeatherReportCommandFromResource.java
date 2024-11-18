package org.hidrobots.platform.report.interfaces.transform;

import org.hidrobots.platform.report.domain.model.commands.CreateWeatherReportCommand;
import org.hidrobots.platform.report.interfaces.resources.CreateWeatherReportResource;

public class WeatherReportCommandFromResource {
        public static CreateWeatherReportCommand toCommandFromResource(CreateWeatherReportResource resource) {
            return new CreateWeatherReportCommand(
                    resource.temperature(),
                    resource.humidity(),
                    resource.deviceId()
            );
        }
}
