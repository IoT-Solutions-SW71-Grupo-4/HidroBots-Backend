package org.hidrobots.platform.deviceCommunication.interfaces.rest.transform;

import org.hidrobots.platform.deviceCommunication.domain.model.commands.AnalyzeSoilCommand;
import org.hidrobots.platform.deviceCommunication.interfaces.rest.resources.AnalyzeSoilCommandResource;

public class AnalyzeSoilCommandFromResource {
    public static AnalyzeSoilCommand fromResource(AnalyzeSoilCommandResource resource) {
        return new AnalyzeSoilCommand(
                resource.deviceCode()
        );
    }
}
