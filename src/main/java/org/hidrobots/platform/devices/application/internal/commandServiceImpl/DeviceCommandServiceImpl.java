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
    public Device connectDevice(ConnectDeviceCommand command) {
        var deviceEntity = this.deviceRepository.findByDeviceCode(command.deviceCode());
        if (deviceEntity.isEmpty()) {
            throw new IllegalArgumentException("Invalid deviceCode: Device does not exist");
        }
        var device = deviceEntity.get();

        if (!cropRepository.existsById(command.cropId())) {
            throw new IllegalArgumentException("Invalid cropId: Crop does not exist");
        }

        device.setCropId(command.cropId());
        this.deviceRepository.save(device);

        return device;
    }

    @Override
    public Device generateDeviceCode() {
        var device = new Device();
        return this.deviceRepository.save(device);
    }
}
