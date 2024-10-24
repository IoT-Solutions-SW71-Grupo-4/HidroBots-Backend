package org.hidrobots.platform.crops.cucumber.getCrops;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hidrobots.platform.crops.application.internal.queryServiceImpl.CropQueryServiceImpl;
import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.hidrobots.platform.crops.domain.model.commands.CreateCropCommand;
import org.hidrobots.platform.crops.domain.model.queries.GetAllCropsQuery;
import org.hidrobots.platform.crops.domain.model.queries.GetCropByIdQuery;
import org.hidrobots.platform.crops.domain.model.valueobjects.IrrigationType;
import org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration
public class CropQueryServiceImplTest {

    @Autowired
    private CropQueryServiceImpl cropQueryService;

    @Autowired
    private CropRepository cropRepository;

    private List<Crop> crops;
    private Crop savedCrop;
    private Optional<Crop> retrievedCrop;

    @Given("there are crops in the repository")
    public void there_are_crops_in_the_repository() {
        cropRepository.deleteAll(); // Limpiar la base de datos antes de agregar cultivos de prueba

        Crop crop1 = new Crop(new CreateCropCommand(
                "Maíz",
                IrrigationType.Manual,
                100L,
                LocalDate.now(),
                1L
        ));

        Crop crop2 = new Crop(new CreateCropCommand(
                "Papa",
                IrrigationType.Automatic,
                200L,
                LocalDate.now(),
                2L
        ));

        cropRepository.save(crop1);
        cropRepository.save(crop2);
    }

    @When("all crops are requested")
    public void all_crops_are_requested() {
        crops = cropQueryService.handle(new GetAllCropsQuery());
    }

    @Then("all crops should be returned")
    public void all_crops_should_be_returned() {
        assertNotNull(crops);
        assertEquals(2, crops.size());
    }

    @Given("a crop is saved in the repository with id {long}")
    public void a_crop_is_saved_in_the_repository_with_id(Long id) {
        cropRepository.deleteAll(); // Limpiar la base de datos antes de agregar cultivos de prueba

        savedCrop = cropRepository.save(new Crop(new CreateCropCommand(
                "Maíz",
                IrrigationType.Manual,
                100L,
                LocalDate.now(),
                1L
        )));
    }

    @When("the crop is requested by id")
    public void the_crop_is_requested_by_id() {
        retrievedCrop = cropQueryService.handle(new GetCropByIdQuery(savedCrop.getId()));
    }

    @Then("the crop should be returned")
    public void the_crop_should_be_returned() {
        assertTrue(retrievedCrop.isPresent());
        assertEquals(savedCrop.getId(), retrievedCrop.get().getId());
    }

    @When("the crop is deleted by id")
    public void the_crop_is_deleted_by_id() {
        cropRepository.deleteById(savedCrop.getId());
    }

    @Then("the crop should no longer exist in the repository")
    public void the_crop_should_no_longer_exist_in_the_repository() {
        Optional<Crop> deletedCrop = cropRepository.findById(savedCrop.getId());
        assertFalse(deletedCrop.isPresent());
    }
}