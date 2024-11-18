package org.hidrobots.platform.report.interfaces.transform;

import org.hidrobots.platform.report.domain.model.commands.CreateSoilReportCommand;
import org.hidrobots.platform.report.interfaces.resources.CreateSoilReportResource;

public class SoilReportCommandFromResource {
    public static CreateSoilReportCommand toCommandFromResource(CreateSoilReportResource resource) {
        return new CreateSoilReportCommand(
                resource.nitrogen(),
                resource.phosphorus(),
                resource.potassium(),
                resource.deviceId()
        );
    }
}
