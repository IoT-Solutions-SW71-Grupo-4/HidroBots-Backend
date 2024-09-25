package org.hidrobots.platform.crops.cucumber.createCrop;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "org.hidrobots.platform.crops.cucumber")
public class CreateCropCucumberTest {
}