package com.upgrade.step_definitions;


import com.upgrade.utils.ConfigurationReader;
import io.cucumber.java.en.*;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class LoanApplicationApiStep {

    Response response;

    @Given("valid URI endpoint")
    public void valid_uri_endpoint() {
        baseURI = ConfigurationReader.getProperty("baseURL");
        basePath = ConfigurationReader.getProperty("basePath") ;
    }

    @When("POST request is sent with valid {string}, {string}, token and payload")
    public void post_request_is_sent_with_valid_token_and_payload(String validUserName, String password) {
        Map<String,String>payloadMap=new LinkedHashMap<>();
        payloadMap.put("username", validUserName);
        payloadMap.put("password", password);
        payloadMap.put("recaptchaToken", ConfigurationReader.getProperty("token"));

        response = given()
                .log().all()
                .header("x-cf-source-id", ConfigurationReader.getProperty("x_source-id"))
                .header("x-cf-corr-id", ConfigurationReader.getProperty("UUID"))
                .contentType(ContentType.JSON)
                .body(payloadMap).when().post();
    }

    @Then("Status code should be {int}")
    public void status_code_should_be(Integer statusCode) {
      response.then().log().body().assertThat().statusCode(is(statusCode));
    }

    @And("Content Type is Json")
    public void content_type_is_json() {
         response.then().log().body().assertThat().contentType(ContentType.JSON);
    }

    @Then("response first name is {string}")
    public void response_first_name_is(String firstName) {
      response.then().log().body().assertThat().body("firstName", is(firstName));
    }

    @Then("response product type is {string}")
    public void response_product_type_is(String productType) {
        response.then().log().body().assertThat().body("loansInReview[0].productType", is(productType));
    }

    @Then("response purpose is {string}")
    public void response_purpose_is(String purpose) {
        response.then().log().body().assertThat().body("loansInReview[0].purpose", is(purpose));
    }


    @When("POST request is sent with invalid {string} in headers and payload")
    public void post_request_is_sent_with_invalid_in_headers_and_payload(String invalidUserName) {
        Map<String,String>payloadMap=new LinkedHashMap<>();
        payloadMap.put("username", invalidUserName);
        payloadMap.put("password", "On$3XcgsW#9q");
        payloadMap.put("recaptchaToken", ConfigurationReader.getProperty("token"));

        response = given()
                .log().all()
                .header("x-cf-source-id", ConfigurationReader.getProperty("x_source-id"))
                .header("x-cf-corr-id", ConfigurationReader.getProperty("UUID"))
                .contentType(ContentType.JSON)
                .body(payloadMap).when().post();
    }

}
