package org.hidrobots.platform.devices.application.internal.queryServiceImpl;

import org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.hidrobots.platform.devices.domain.model.entities.Device;
import org.hidrobots.platform.devices.domain.services.DeviceQueryService;
import org.hidrobots.platform.devices.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.hidrobots.platform.profiles.infrastructure.persistence.jpa.repositories.FarmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceQueryServiceImpl implements DeviceQueryService {

    private final DeviceRepository deviceRepository;

    public DeviceQueryServiceImpl(DeviceRepository deviceRepository, CropRepository cropRepository, FarmerRepository farmerRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> getDevicesByFarmerId(Long farmerId) {
        return deviceRepository.findByFarmerId(farmerId);
    }

    @Override
    public List<Device> getDevicesByCropId(Long cropId) {
        return deviceRepository.findByCropId(cropId);
    }

    @Override
    public Optional<Device> getDeviceById(Long deviceId) {
        return deviceRepository.findById(deviceId);
    }
}
