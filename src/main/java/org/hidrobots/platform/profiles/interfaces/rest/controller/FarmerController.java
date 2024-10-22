package org.hidrobots.platform.profiles.interfaces.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hidrobots.platform.profiles.domain.model.queries.GetAllFarmersQuery;
import org.hidrobots.platform.profiles.domain.model.queries.GetFarmerByIdQuery;
import org.hidrobots.platform.profiles.domain.services.FarmerCommandService;
import org.hidrobots.platform.profiles.domain.services.FarmerQueryService;
import org.hidrobots.platform.profiles.interfaces.rest.resources.CreateFarmerResource;
import org.hidrobots.platform.profiles.interfaces.rest.resources.FarmerResource;
import org.hidrobots.platform.profiles.interfaces.rest.resources.UpdateFarmerResource;
import org.hidrobots.platform.profiles.interfaces.rest.transform.CreateFarmerResourceCommandFromResourceAssembler;
import org.hidrobots.platform.profiles.interfaces.rest.transform.FarmerResourceFromEntityAssembler;
import org.hidrobots.platform.profiles.interfaces.rest.transform.UpdateFarmerResourceCommandFromResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/farmers", produces = "application/json")
@Tag(name = "Farmers", description = "Farmers API")
@CrossOrigin(origins = "*")
public class FarmerController {

    private final FarmerCommandService farmerCommandService;
    private final FarmerQueryService farmerQueryService;


    public FarmerController(FarmerCommandService farmerCommandService, FarmerQueryService farmerQueryService) {
        this.farmerCommandService = farmerCommandService;
        this.farmerQueryService = farmerQueryService;
    }

    @PostMapping
    public ResponseEntity<FarmerResource> createFarmer(@Valid @RequestBody CreateFarmerResource createFarmerResource) {

        var createFarmerCommand = CreateFarmerResourceCommandFromResourceAssembler.toCommandFromResource(createFarmerResource);
        var farmerId = farmerCommandService.createFarmer(createFarmerCommand);
        if (farmerId == null) {
            return ResponseEntity.badRequest().build();
        }

        var getFarmerByIdQuery = new GetFarmerByIdQuery(farmerId);
        var farmer = farmerQueryService.getFarmerById(getFarmerByIdQuery);
        if (farmer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var farmerResource = FarmerResourceFromEntityAssembler.toResourceFromEntity(farmer.get());
        return new ResponseEntity<>(farmerResource, HttpStatus.CREATED);

    }

    @GetMapping("/{farmerId}")
    public ResponseEntity<FarmerResource> getFarmerById(@PathVariable Long farmerId) {
        var getFarmerByIdQuery = new GetFarmerByIdQuery(farmerId);
        var farmer = farmerQueryService.getFarmerById(getFarmerByIdQuery);
        if (farmer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var farmerResource = FarmerResourceFromEntityAssembler.toResourceFromEntity(farmer.get());
        return ResponseEntity.ok(farmerResource);
    }

    @GetMapping
    public ResponseEntity<List<FarmerResource>> getAllFarmers() {
        var getAllFarmerQuery = new GetAllFarmersQuery();
        var farmers = farmerQueryService.getAllFarmers(getAllFarmerQuery);
        return ResponseEntity.ok(farmers.stream().map(FarmerResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList()));
    }

    @PutMapping("/{farmerId}")
    public ResponseEntity<FarmerResource> updateFarmer(@Valid @PathVariable Long farmerId, @RequestBody UpdateFarmerResource updateFarmerResource) {
        var updateFarmerCommand = UpdateFarmerResourceCommandFromResourceAssembler.toCommandFromResource(updateFarmerResource, farmerId);
        var updatedFarmer = farmerCommandService.updateFarmer(updateFarmerCommand);
        if (updatedFarmer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var farmerResource = FarmerResourceFromEntityAssembler.toResourceFromEntity(updatedFarmer.get());
        return ResponseEntity.ok(farmerResource);
    }

    @DeleteMapping("/{farmerId}")
    public ResponseEntity<?> deleteFarmer(@PathVariable Long farmerId) {
        farmerCommandService.deleteFarmer(farmerId);
        return ResponseEntity.ok("Farmer with given id successfully deleted");
    }
}
