package org.hidrobots.platform.crops.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.hidrobots.platform.crops.domain.model.commands.DeleteCropCommand;
import org.hidrobots.platform.crops.domain.model.commands.UpdateCropDescriptionCommand;
import org.hidrobots.platform.crops.domain.model.commands.UpdateCropNameCommand;
import org.hidrobots.platform.crops.domain.model.queries.GetAllCropsQuery;
import org.hidrobots.platform.crops.domain.model.queries.GetCropByIdQuery;
import org.hidrobots.platform.crops.domain.services.CropCommandService;
import org.hidrobots.platform.crops.domain.services.CropQueryService;
import org.hidrobots.platform.crops.interfaces.rest.resources.CreateCropResource;
import org.hidrobots.platform.crops.interfaces.rest.resources.CropResource;
import org.hidrobots.platform.crops.interfaces.rest.resources.UpdateCropDescriptionResource;
import org.hidrobots.platform.crops.interfaces.rest.resources.UpdateCropNameResource;
import org.hidrobots.platform.crops.interfaces.rest.transform.CreateCropResourceCommandFromResourceAssembler;
import org.hidrobots.platform.crops.interfaces.rest.transform.CropResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Crops", description = "Crops management")
@RequestMapping(value = "api/v1/crops", produces = MediaType.APPLICATION_JSON_VALUE)
public class CropsController {

    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;

    @Autowired
    public CropsController(CropCommandService cropCommandService, CropQueryService cropQueryService) {
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
    }

    @GetMapping
    public ResponseEntity<List<CropResource>> getAllCrops() {
        var getAllCropsQuery = new GetAllCropsQuery();
        var crops = cropQueryService.handle(getAllCropsQuery);

        var cropResources = crops.stream()
                .map(CropResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(cropResources);
    }

    @PostMapping
    public ResponseEntity<CropResource> createCrop(@Valid @RequestBody CreateCropResource createCropResource) {
        var createCropCommand = CreateCropResourceCommandFromResourceAssembler.toCommandFromResource(createCropResource);
        var cropId = cropCommandService.handle(createCropCommand);

        return cropQueryService.handle(new GetCropByIdQuery(cropId))
                .map(cropEntity -> new ResponseEntity<>(
                        CropResourceFromEntityAssembler.toResourceFromEntity(cropEntity), HttpStatus.CREATED))
                .orElse(ResponseEntity.badRequest().build());

    }

    @GetMapping("/{cropId}")
    public ResponseEntity<CropResource> getCropById(@PathVariable Long cropId) {
        return cropQueryService.handle(new GetCropByIdQuery(cropId))
                .map(cropEntity -> new ResponseEntity<>(
                        CropResourceFromEntityAssembler.toResourceFromEntity(cropEntity), HttpStatus.OK))
                .orElse(ResponseEntity.notFound().header(
                        "message", "Crop with id " + cropId + " not found"
                ).build());
    }

    @PatchMapping("/{cropId}/cropName")
    public ResponseEntity<CropResource> updateCropName(@PathVariable Long cropId, @RequestBody UpdateCropNameResource updateCropNameResource) {
        var updateCropNameCommand = new UpdateCropNameCommand(
                cropId,
                updateCropNameResource.cropName()
        );

        Optional<Crop> updatedCrop = cropCommandService.handle(updateCropNameCommand);

        if (updatedCrop.isEmpty()) {
            return ResponseEntity.notFound().header(
                    "message", "Crop with id " + cropId + " not found"
            ).build();
        }

        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(updatedCrop.get());
        return ResponseEntity.ok(cropResource);
    }

    @PatchMapping("/{cropId}/cropDescription")
    public ResponseEntity<CropResource> updateCropDescription(@PathVariable Long cropId, @RequestBody UpdateCropDescriptionResource updateCropDescriptionResource) {
        var updateCropDescriptionCommand = new UpdateCropDescriptionCommand(
                cropId,
                updateCropDescriptionResource.cropDescription()
        );

        Optional<Crop> updatedCrop = cropCommandService.handle(updateCropDescriptionCommand);

        if (updatedCrop.isEmpty()) {
            return ResponseEntity.notFound().header(
                    "message", "Crop with id " + cropId + " not found"
            ).build();
        }

        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(updatedCrop.get());
        return ResponseEntity.ok(cropResource);
    }

    @DeleteMapping("/{cropId}")
    public ResponseEntity<?> deleteCrop(@PathVariable Long cropId) {
        var deleteCropCommand = new DeleteCropCommand(cropId);
        cropCommandService.handle(deleteCropCommand);

        return ResponseEntity.ok("Crop with id " + cropId + " deleted");
    }
}