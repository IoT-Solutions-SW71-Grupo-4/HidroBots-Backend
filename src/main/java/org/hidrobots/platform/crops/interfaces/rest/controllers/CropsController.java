package org.hidrobots.platform.crops.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hidrobots.platform.crops.application.internal.commandServiceImpl.CropCommandServiceImpl;
import org.hidrobots.platform.crops.application.internal.commandServiceImpl.CropImageServiceImpl;
import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.hidrobots.platform.crops.domain.model.commands.DeleteCropCommand;
import org.hidrobots.platform.crops.domain.model.commands.UpdateCropCommand;
import org.hidrobots.platform.crops.domain.model.commands.UpdateCropImageCommand;
import org.hidrobots.platform.crops.domain.model.commands.UpdateIrrigationTypeCommand;
import org.hidrobots.platform.crops.domain.model.entities.CropImage;
import org.hidrobots.platform.crops.domain.model.queries.GetAllCropsQuery;
import org.hidrobots.platform.crops.domain.model.queries.GetCropByIdQuery;
import org.hidrobots.platform.crops.domain.services.CropCommandService;
import org.hidrobots.platform.crops.domain.services.CropQueryService;
import org.hidrobots.platform.crops.interfaces.rest.resources.*;
import org.hidrobots.platform.crops.interfaces.rest.transform.CreateCropResourceCommandFromResourceAssembler;
import org.hidrobots.platform.crops.interfaces.rest.transform.CropResourceFromEntityAssembler;
import org.hidrobots.platform.crops.interfaces.rest.transform.UpdateCropImageResourceFromResourceAssembler;
import org.hidrobots.platform.crops.interfaces.rest.transform.UpdateCropResourceCommandFromResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Crops", description = "Crops management")
@RequestMapping(value = "api/v1/crops", produces = MediaType.APPLICATION_JSON_VALUE)
public class CropsController {

    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;
    private final CropCommandServiceImpl cropCommandServiceImpl;
    private final CropImageServiceImpl cropImageServiceImpl;

    @Autowired
    public CropsController(CropCommandService cropCommandService, CropQueryService cropQueryService, CropCommandServiceImpl cropCommandServiceImpl, CropImageServiceImpl cropImageServiceImpl) {
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
        this.cropCommandServiceImpl = cropCommandServiceImpl;
        this.cropImageServiceImpl = cropImageServiceImpl;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CropResource> createCrop(
            @RequestPart("file") MultipartFile file,
            @RequestPart("crop") @Valid CreateCropResource createCropResource) throws IOException {

        // Crear el comando de cultivo
        var createCropCommand = CreateCropResourceCommandFromResourceAssembler.toCommandFromResource(createCropResource);

        // Llamar al servicio que maneja tanto la creación del cultivo como la subida de la imagen
        Long cropId = cropCommandService.handle(createCropCommand, file);

        // Devolver el recurso del cultivo creado
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

    @PutMapping("/{cropId}")
    public ResponseEntity<CropResource> updateCrop(@PathVariable Long cropId, @RequestBody UpdateCropResource updateCropResource) {
        var updateCropCommand = UpdateCropResourceCommandFromResourceAssembler.toCommandFromResource(cropId, updateCropResource);

        var updatedCrop = cropCommandService.handle(updateCropCommand); // actualizamos sin la imagen

        if (updatedCrop.isEmpty()) {
            return ResponseEntity.notFound().header(
                    "message", "Crop with id " + cropId + " not found"
            ).build();
        }

        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(updatedCrop.get());
        return ResponseEntity.ok(cropResource);
    }


    @PutMapping(value = "/{cropId}/cropImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CropResource> updateCropImage(
            @PathVariable Long cropId,
            @RequestPart("file") MultipartFile file) throws IOException {

        // Encontramos el cultivo por ID
        Optional<Crop> cropOptional = cropQueryService.handle(new GetCropByIdQuery(cropId));

        if (cropOptional.isEmpty()) {
            return ResponseEntity.notFound().header("message", "Crop with id " + cropId + " not found").build();
        }

        Crop crop = cropOptional.get();

        // Subimos la nueva imagen a Cloudinary y eliminamos la anterior si existe
        Optional<Crop> updatedCropOptional = cropCommandService.UpdateCropImage(file, crop);

        if (updatedCropOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        Crop updatedCrop = updatedCropOptional.get();

        // Convertimos el cultivo a un recurso para devolverlo al cliente
        CropResource cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(updatedCrop);

        return ResponseEntity.ok(cropResource);
    }


    @PatchMapping("/{cropId}/irrigationType")
    public ResponseEntity<CropResource> updateIrrigationType(@PathVariable Long cropId, @RequestBody UpdateIrrigationTypeResource updateIrrigationTypeResource) {
        var updateIrrigationTypeCommand = new UpdateIrrigationTypeCommand(
                cropId,
                updateIrrigationTypeResource.irrigationType()
        );

        Optional<Crop> updatedIrrigation = cropCommandService.handle(updateIrrigationTypeCommand);

        if (updatedIrrigation.isEmpty()) {
            return ResponseEntity.notFound().header(
                    "message", "Crop with id " + cropId + " not found"
            ).build();
        }

        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(updatedIrrigation.get());
        return ResponseEntity.ok(cropResource);
    }

    @DeleteMapping("/{cropId}")
    public ResponseEntity<?> deleteCrop(@PathVariable Long cropId) {
        var deleteCropCommand = new DeleteCropCommand(cropId);
        cropCommandService.handle(deleteCropCommand);

        return ResponseEntity.ok("Crop with id " + cropId + " deleted");
    }

    @DeleteMapping("/{cropId}/cropImage")
    public ResponseEntity<CropResource> deleteCropImage(@PathVariable Long cropId) throws IOException {
        // Delegar al servicio para que maneje la eliminación de la imagen
        Optional<Crop> updatedCropOptional = cropCommandService.deleteCropImage(cropId);

        if (updatedCropOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        Crop updatedCrop = updatedCropOptional.get();

        // Convertir el cultivo actualizado a un recurso para devolverlo al cliente
        CropResource cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(updatedCrop);

        return ResponseEntity.ok(cropResource);
    }



}