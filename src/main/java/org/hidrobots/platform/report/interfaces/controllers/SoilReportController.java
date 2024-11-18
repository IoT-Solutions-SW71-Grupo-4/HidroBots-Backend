package org.hidrobots.platform.report.interfaces.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hidrobots.platform.report.domain.service.SoilReportCommandService;
import org.hidrobots.platform.report.domain.service.SoilReportQueryService;
import org.hidrobots.platform.report.interfaces.resources.CreateSoilReportResource;
import org.hidrobots.platform.report.interfaces.resources.SoilReportResource;
import org.hidrobots.platform.report.interfaces.transform.SoilReportCommandFromResource;
import org.hidrobots.platform.report.interfaces.transform.SoilReportResourceFromClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Soil Report", description = "Soil Report management")
@RequestMapping(value = "api/v1/soilReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class SoilReportController {
    private final SoilReportCommandService soilReportCommandService;
    private final SoilReportQueryService soilReportQueryService;

    public SoilReportController(SoilReportCommandService soilReportCommandService, SoilReportQueryService soilReportQueryService) {
        this.soilReportCommandService = soilReportCommandService;
        this.soilReportQueryService = soilReportQueryService;
    }

    @PostMapping
    public ResponseEntity<SoilReportResource> createSoilReport(@Valid @RequestBody CreateSoilReportResource createSoilReportResource) {
        var createSoilReportCommand = SoilReportCommandFromResource.toCommandFromResource(createSoilReportResource);
        var soilReportId = soilReportCommandService.createSoilReport(createSoilReportCommand);

        if (soilReportId == null) {
            return ResponseEntity.badRequest().build();
        }

        var soilReport = soilReportQueryService.getReportById(soilReportId);
        var soilReportResource = SoilReportResourceFromClass.toResourceFromClass(soilReport.get());
        return new ResponseEntity<>(soilReportResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SoilReportResource>> getAllSoilReport() {
        var soilReports = soilReportQueryService.getAllReport();
        return ResponseEntity.ok(soilReports.stream().map(SoilReportResourceFromClass::toResourceFromClass).collect(Collectors.toList()));
    }

    @GetMapping("/last")
    public ResponseEntity<SoilReportResource> getLastSoilReport() {
        var soilReport = soilReportQueryService.getLastReport();
        var soilReportResource = SoilReportResourceFromClass.toResourceFromClass(soilReport.get());
        return new ResponseEntity<>(soilReportResource, HttpStatus.CREATED);
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<List<SoilReportResource>> getAllSoilReportByCropId(@PathVariable Long cropId) {
        var soilReports = soilReportQueryService.getAllReportByCropId(cropId);
        return ResponseEntity.ok(soilReports.stream().map(SoilReportResourceFromClass::toResourceFromClass).collect(Collectors.toList()));
    }

    @GetMapping("/last/{cropId}")
    public ResponseEntity<SoilReportResource> getLastSoilReportByCropId(@PathVariable Long cropId) {
        var soilReport = soilReportQueryService.getLastReportByCropId(cropId);
        var soilReportResource = SoilReportResourceFromClass.toResourceFromClass(soilReport.get());
        return new ResponseEntity<>(soilReportResource, HttpStatus.CREATED);
    }
}
