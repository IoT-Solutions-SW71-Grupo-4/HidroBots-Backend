package org.hidrobots.platform.deviceCommunication.interfaces.rest.transform;

import org.hidrobots.platform.deviceCommunication.domain.model.commands.IrrigationCommand;
import org.hidrobots.platform.deviceCommunication.interfaces.rest.resources.IrrigationCommandResource;

public class IrrigationCommandFromResource {
    public static IrrigationCommand fromResource(IrrigationCommandResource resource) {
        return new IrrigationCommand(resource.deviceCode(), resource.irrigationStatus());
    }
}
