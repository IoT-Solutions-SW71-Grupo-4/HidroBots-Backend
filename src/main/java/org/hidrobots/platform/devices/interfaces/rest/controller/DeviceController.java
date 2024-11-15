package org.hidrobots.platform.devices.interfaces.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hidrobots.platform.devices.domain.model.commands.CreateDeviceCommand;
import org.hidrobots.platform.devices.domain.services.DeviceCommandService;
import org.hidrobots.platform.devices.domain.services.DeviceQueryService;
import org.hidrobots.platform.devices.interfaces.rest.resources.DeviceResource;
import org.hidrobots.platform.devices.interfaces.rest.transform.DeviceResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "api/v1/devices", produces = "application/json")
@Tag(name = "Devices", description = "Devices API")
@CrossOrigin(origins = "*")
public class DeviceController {

    private final DeviceCommandService deviceCommandService;
    private final DeviceQueryService deviceQueryService;

    public DeviceController(DeviceCommandService deviceCommandService, DeviceQueryService deviceQueryService) {
        this.deviceCommandService = deviceCommandService;
        this.deviceQueryService = deviceQueryService;
    }

    @Operation(
            summary = "Create device",
            description = "Create a new device"
    )
    @PostMapping("/crop/{cropId}")
    public ResponseEntity<DeviceResource> createDevice(@PathVariable Long cropId) {
        var createDeviceCommand = new CreateDeviceCommand(cropId);

        var deviceId = deviceCommandService.createDevice(createDeviceCommand);
        if (deviceId == null) {
            return ResponseEntity.badRequest().build();
        }

        var device = deviceQueryService.getDeviceById(deviceId);
        if (device.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return new ResponseEntity<>(deviceResource, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Connect device",
            description = "Connect an existing device"
    )
    @PostMapping("/{deviceId}/connect")
    public ResponseEntity<?> connectDevice(@PathVariable Long deviceId) {
        return null;
    }

    @Operation(
            summary = "Get device by farmer ID",
            description = "Get a device by its associated farmer ID"
    )
    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<DeviceResource>> getDeviceByFarmerId(@PathVariable Long farmerId) {
        var devices = deviceQueryService.getDevicesByFarmerId(farmerId);
        if (devices.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                devices.stream().map(DeviceResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList())
        );
    }

    @Operation(
            summary = "Get device by crop ID",
            description = "Get a device by its associated crop ID"
    )
    @GetMapping("/crop/{cropId}")
    public ResponseEntity<List<DeviceResource>> getDeviceByCropId(@PathVariable Long cropId) {
        var devices = deviceQueryService.getDevicesByCropId(cropId);
        if (devices.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                devices.stream().map(DeviceResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList())
        );
    }
}