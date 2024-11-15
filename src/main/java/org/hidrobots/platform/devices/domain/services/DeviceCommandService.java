package org.hidrobots.platform.devices.domain.services;

import org.hidrobots.platform.devices.domain.model.commands.ConnectDeviceCommand;
import org.hidrobots.platform.devices.domain.model.commands.CreateDeviceCommand;

public interface DeviceCommandService {
    Long createDevice(CreateDeviceCommand command);
    void connectDevice(ConnectDeviceCommand command);
}
