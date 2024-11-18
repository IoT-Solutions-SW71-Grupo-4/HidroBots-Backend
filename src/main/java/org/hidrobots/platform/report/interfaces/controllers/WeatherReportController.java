package org.hidrobots.platform.report.interfaces.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hidrobots.platform.profiles.interfaces.rest.resources.FarmerResource;
import org.hidrobots.platform.profiles.interfaces.rest.transform.FarmerResourceFromEntityAssembler;
import org.hidrobots.platform.report.domain.service.WeatherReportCommandService;
import org.hidrobots.platform.report.domain.service.WeatherReportQueryService;
import org.hidrobots.platform.report.interfaces.resources.CreateWeatherReportResource;
import org.hidrobots.platform.report.interfaces.resources.WeatherReportResource;
import org.hidrobots.platform.report.interfaces.transform.WeatherReportCommandFromResource;
import org.hidrobots.platform.report.interfaces.transform.WeatherReportResourceFromClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Weather Report", description = "Weather Report management")
@RequestMapping(value = "api/v1/weatherReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherReportController {

    private final WeatherReportCommandService weatherReportCommandService;
    private final WeatherReportQueryService weatherReportQueryService;

    public WeatherReportController(WeatherReportCommandService weatherReportCommandService, WeatherReportQueryService weatherReportQueryService) {
        this.weatherReportCommandService = weatherReportCommandService;
        this.weatherReportQueryService = weatherReportQueryService;
    }

    @PostMapping
    public ResponseEntity<WeatherReportResource> createWeatherReport(@Valid @RequestBody CreateWeatherReportResource createWeatherReportResource) {
        var createWeatherReportCommand = WeatherReportCommandFromResource.toCommandFromResource(createWeatherReportResource);
        var weatherReportId = weatherReportCommandService.createWeatherReport(createWeatherReportCommand);

        if (weatherReportId == null) {
            return ResponseEntity.badRequest().build();
        }

        var weatherReport = weatherReportQueryService.getReportById(weatherReportId);
        var weatherReportResource = WeatherReportResourceFromClass.toResourceFromClass(weatherReport.get());
        return new ResponseEntity<>(weatherReportResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WeatherReportResource>> getAllWeatherReport() {
        var weatherReports = weatherReportQueryService.getAllReport();
        return ResponseEntity.ok(weatherReports.stream().map(WeatherReportResourceFromClass::toResourceFromClass).collect(Collectors.toList()));
    }

    @GetMapping("/last")
    public ResponseEntity<WeatherReportResource> getLastWeatherReport() {
        var weatherReport = weatherReportQueryService.getLastReport();
        var weatherReportResource = WeatherReportResourceFromClass.toResourceFromClass(weatherReport.get());
        return new ResponseEntity<>(weatherReportResource, HttpStatus.CREATED);
    }

    @GetMapping("{cropId}")
    public ResponseEntity<List<WeatherReportResource>> getAllWeatherReportByCropId(@PathVariable Long  cropId) {
        var weatherReports = weatherReportQueryService.getAllReportByCropId(cropId);
        return ResponseEntity.ok(weatherReports.stream().map(WeatherReportResourceFromClass::toResourceFromClass).collect(Collectors.toList()));
    }

    @GetMapping("/last/{cropId}")
    public ResponseEntity<WeatherReportResource> getLastWeatherReportByCropId(@PathVariable Long cropId) {
        var weatherReport = weatherReportQueryService.getLastReportByCropId(cropId);
        var weatherReportResource = WeatherReportResourceFromClass.toResourceFromClass(weatherReport.get());
        return new ResponseEntity<>(weatherReportResource, HttpStatus.CREATED);
    }




}
