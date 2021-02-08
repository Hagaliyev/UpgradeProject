package com.upgrade.step_definitions;

import com.upgrade.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class HooksStep {
    @Before
    public  void setup(Scenario scenario){
        if (scenario.getSourceTagNames().equals("@UiTest")){
            Driver.getDriver().manage().window().maximize();
            Driver.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }

    }


    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            byte[] data= ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", scenario.getName());
        }
        //Driver.closeDriver();
    }

}
