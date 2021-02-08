package com.upgrade.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/features",
        glue = "com/upgrade/step_definitions",
        plugin = {"json:target/cucumber.json"},
        tags = "",
        dryRun = false

)

/**
 * Test can be run in different ways
 * 1.Test can be run directly from runner class by entering corresponding tag name of scenarios
 * 2.Through terminal by mvn test and mvn verify commands
 * In order to get detailed html-report regarding results of test please please use mvn verify command through terminal
 * and report will be generated in target folder under cucumber-html-reports folder.
 * By clicking to .html files and selecting browser option in opened page you can view the results of the test
 */

public class TestRunner {

}
