package com.upgrade.step_definitions;

import com.upgrade.utils.ConfigurationReader;
import org.junit.After;
import org.junit.Before;

import static io.restassured.RestAssured.*;

public class HooksAPIStep {

    @Before()
    public static void setUp(){
    //this step is for demo purposes.

    }

    @After
    public static void tearDown(){
        reset();
    }
}
