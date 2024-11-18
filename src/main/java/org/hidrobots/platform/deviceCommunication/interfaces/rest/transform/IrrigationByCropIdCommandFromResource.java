package org.hidrobots.platform.deviceCommunication.interfaces.rest.transform;

import org.hidrobots.platform.deviceCommunication.domain.model.commands.IrrigationByCropIdCommand;
import org.hidrobots.platform.deviceCommunication.interfaces.rest.resources.IrrigationByCropIdResource;

public class IrrigationByCropIdCommandFromResource {
    public static IrrigationByCropIdCommand fromResource(IrrigationByCropIdResource resource) {
        return new IrrigationByCropIdCommand(resource.cropId(), resource.irrigationStatus());
    }
}
