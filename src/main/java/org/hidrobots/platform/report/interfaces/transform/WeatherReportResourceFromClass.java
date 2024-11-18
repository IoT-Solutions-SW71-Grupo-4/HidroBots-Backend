package org.hidrobots.platform.report.interfaces.transform;

import org.hidrobots.platform.report.domain.model.entities.WeatherReport;
import org.hidrobots.platform.report.interfaces.resources.WeatherReportResource;

public class WeatherReportResourceFromClass {

    public static WeatherReportResource toResourceFromClass(WeatherReport weatherReport) {
        return new WeatherReportResource(
                weatherReport.getId(),
                weatherReport.getDateTime(),
                weatherReport.getTemperature(),
                weatherReport.getHumidity(),
                weatherReport.getDeviceId()
        );
    }
}
