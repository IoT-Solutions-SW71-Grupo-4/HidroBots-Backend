package org.hidrobots.platform.deviceCommunication.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hidrobots.platform.deviceCommunication.domain.services.SoilAnalysisNotifier;
import org.hidrobots.platform.deviceCommunication.interfaces.rest.resources.AnalyzeSoilCommandResource;
import org.hidrobots.platform.deviceCommunication.interfaces.rest.transform.AnalyzeSoilCommandFromResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Soil Analysis", description = "Soil Analysis management")
@RequestMapping(value = "api/v1/soil_analysis", produces = MediaType.APPLICATION_JSON_VALUE)
public class SoilAnalysisController {
    @Autowired
    private SoilAnalysisNotifier soilAnalysisNotifier;

    @Operation(
        summary = "Send soil analysis request to the device"
    )
    @PostMapping("/analyze")
    public ResponseEntity<String> analyzeSoil(@RequestBody AnalyzeSoilCommandResource resource) {
        var command = AnalyzeSoilCommandFromResource.fromResource(resource);
        var response = soilAnalysisNotifier.sendAnalysisRequestToDevice(command);
        return ResponseEntity.ok(response);
    }
}
