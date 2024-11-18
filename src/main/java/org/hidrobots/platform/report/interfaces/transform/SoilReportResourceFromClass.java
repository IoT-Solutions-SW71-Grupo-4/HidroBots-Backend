package org.hidrobots.platform.report.interfaces.transform;

import org.hidrobots.platform.report.domain.model.entities.SoilReport;
import org.hidrobots.platform.report.interfaces.resources.SoilReportResource;

public class SoilReportResourceFromClass {
    public static SoilReportResource toResourceFromClass(SoilReport soilReport) {
        return new SoilReportResource(
                soilReport.getId(),
                soilReport.getDateTime(),
                soilReport.getNitrogen(),
                soilReport.getPhosphorus(),
                soilReport.getPotassium(),
                soilReport.getDeviceId()
        );
    }
}
