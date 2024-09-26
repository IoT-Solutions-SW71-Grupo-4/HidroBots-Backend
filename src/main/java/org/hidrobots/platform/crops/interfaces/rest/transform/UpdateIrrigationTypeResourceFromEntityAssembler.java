package org.hidrobots.platform.crops.interfaces.rest.transform;

import org.hidrobots.platform.crops.domain.model.commands.UpdateIrrigationTypeCommand;
import org.hidrobots.platform.crops.interfaces.rest.resources.UpdateIrrigationTypeResource;

public class UpdateIrrigationTypeResourceFromEntityAssembler {
    public static UpdateIrrigationTypeCommand toCommandFromResource(Long id, UpdateIrrigationTypeResource resource) {
        return new UpdateIrrigationTypeCommand(
                id,
                resource.irrigationType()
        );
    }
}
