package org.hidrobots.platform.iam.cucumber.signUp;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/Iam", glue = "org.hidrobots.platform.iam.cucumber.signUp")
public class SignUpTest {
}