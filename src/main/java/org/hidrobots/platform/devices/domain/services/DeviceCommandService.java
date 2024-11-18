package org.hidrobots.platform.devices.domain.services;

import org.hidrobots.platform.devices.domain.model.commands.ConnectDeviceCommand;
import org.hidrobots.platform.devices.domain.model.commands.CreateDeviceCommand;
import org.hidrobots.platform.devices.domain.model.entities.Device;

public interface DeviceCommandService {
    Long createDevice(CreateDeviceCommand command);
    Device connectDevice(ConnectDeviceCommand command);
    Device generateDeviceCode();
}
