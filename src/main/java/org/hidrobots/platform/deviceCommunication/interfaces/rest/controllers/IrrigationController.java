package org.hidrobots.platform.deviceCommunication.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hidrobots.platform.deviceCommunication.domain.services.IrrigationNotifier;
import org.hidrobots.platform.deviceCommunication.interfaces.rest.resources.IrrigationByCropIdResource;
import org.hidrobots.platform.deviceCommunication.interfaces.rest.resources.IrrigationCommandResource;
import org.hidrobots.platform.deviceCommunication.interfaces.rest.transform.IrrigationByCropIdCommandFromResource;
import org.hidrobots.platform.deviceCommunication.interfaces.rest.transform.IrrigationCommandFromResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Irrigation", description = "Irrigation management")
@RequestMapping(value = "api/v1/irrigation", produces = MediaType.APPLICATION_JSON_VALUE)
public class IrrigationController {
    @Autowired
    private IrrigationNotifier irrigationNotifier;

    @Operation(
            summary = "Send irrigation request to the device"
    )
    @PostMapping
    public ResponseEntity<String> notifyIrrigationSystem(IrrigationCommandResource resource) {
        var command = IrrigationCommandFromResource.fromResource(resource);
        var response = irrigationNotifier.notifyIrrigationSystem(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/irrigate-crop")
    public ResponseEntity<String> notifyIrrigationByCropIdSystem(IrrigationByCropIdResource resource) {
        var command = IrrigationByCropIdCommandFromResource.fromResource(resource);
        var response = irrigationNotifier.notifyIrrigationSystemForCrop(command);
        return ResponseEntity.ok(response);
    }
}
