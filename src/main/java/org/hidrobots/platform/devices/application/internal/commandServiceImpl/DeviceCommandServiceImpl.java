package org.hidrobots.platform.devices.application.internal.commandServiceImpl;

import org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.hidrobots.platform.devices.domain.model.commands.ConnectDeviceCommand;
import org.hidrobots.platform.devices.domain.model.commands.CreateDeviceCommand;
import org.hidrobots.platform.devices.domain.model.entities.Device;
import org.hidrobots.platform.devices.domain.services.DeviceCommandService;
import org.hidrobots.platform.devices.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceCommandServiceImpl implements DeviceCommandService {

    private final DeviceRepository deviceRepository;
    private final CropRepository cropRepository;

    @Autowired
    public DeviceCommandServiceImpl(DeviceRepository deviceRepository, CropRepository cropRepository) {
        this.deviceRepository = deviceRepository;
        this.cropRepository = cropRepository;
    }

    @Override
    public Long createDevice(CreateDeviceCommand command) {

        if (command.cropId() == null || !cropRepository.existsById(command.cropId())) {
            throw new IllegalArgumentException("Invalid cropId: Crop does not exist");
        }

        var device = new Device(command);
        this.deviceRepository.save(device);
        return device.getId();
    }



    @Override
    public void connectDevice(ConnectDeviceCommand command) {
    }
}
