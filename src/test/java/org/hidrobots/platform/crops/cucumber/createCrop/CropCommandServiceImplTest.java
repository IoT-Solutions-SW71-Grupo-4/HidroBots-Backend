package org.hidrobots.platform.crops.cucumber.createCrop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hidrobots.platform.crops.application.internal.commandServiceImpl.CropCommandServiceImpl;
import org.hidrobots.platform.crops.domain.model.aggregates.Crop;
import org.hidrobots.platform.crops.domain.model.commands.CreateCropCommand;
import org.hidrobots.platform.crops.domain.model.valueobjects.IrrigationType;
import org.hidrobots.platform.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration
public class CropCommandServiceImplTest {

    @Autowired
    private CropCommandServiceImpl cropCommandService;

    @Autowired
    private CropRepository cropRepository;

    private CreateCropCommand createCropCommand;
    private Long cropId;

    @Given("a crop with name {string} and irrigation type {string}")
    public void a_crop_with_name_and_irrigation_type(String name, String irrigationType) {
        createCropCommand = new CreateCropCommand(name, IrrigationType.valueOf(irrigationType), 100L, LocalDate.now(), 1L);
    }

    @When("the crop is created")
    public void the_crop_is_created() {
        cropId = cropCommandService.handle(createCropCommand);
    }

    @Then("the crop should be saved in the repository")
    public void the_crop_should_be_saved_in_the_repository() {
        Optional<Crop> crop = cropRepository.findById(cropId);
        assertTrue(crop.isPresent());
        assertEquals(createCropCommand.cropName(), crop.get().getCropName());
    }

    @Then("the crop id should be returned")
    public void the_crop_id_should_be_returned() {
        assertNotNull(cropId);
    }
}