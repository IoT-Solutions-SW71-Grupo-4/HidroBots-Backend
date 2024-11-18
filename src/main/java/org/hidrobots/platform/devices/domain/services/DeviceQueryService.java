package org.hidrobots.platform.devices.domain.services;

import org.hidrobots.platform.devices.domain.model.entities.Device;
import org.hidrobots.platform.report.domain.model.entities.WeatherReport;

import java.util.List;
import java.util.Optional;

public interface DeviceQueryService {

    // Obtener lista de dispositivos por ID de usuario
    List<Device> getDevicesByFarmerId(Long farmerId);

    // Obtener lista de dispositivos por ID de cultivo
    List<Device> getDevicesByCropId(Long cropId);


    Optional<Device> getDeviceById(Long deviceId);
}
