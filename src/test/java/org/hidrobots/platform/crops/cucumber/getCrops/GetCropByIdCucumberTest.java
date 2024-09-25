package org.hidrobots.platform.crops.cucumber.getCrops;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/Crop", glue = "org.hidrobots.platform.crops.cucumber")
public class GetCropByIdCucumberTest {
}