package org.hidrobots.platform.devices.interfaces.rest.transform;

import org.hidrobots.platform.devices.domain.model.entities.Device;
import org.hidrobots.platform.devices.interfaces.rest.resources.DeviceResource;

public record DeviceResourceFromEntityAssembler() {

    public static DeviceResource toResourceFromEntity(Device device) {
        return new DeviceResource(
                device.getId(),
                device.getCropId(),
                device.getDeviceCode()
        );
    }
}
