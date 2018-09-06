package com.tsukhu.demo.steps;

import com.tsukhu.demo.SpringIntegrationTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SwapiSteps extends SpringIntegrationTest {


    private String ENDPOINT_GET_PEOPLE_BY_ID = "https://swapi.co/api/people/";

    @When("a user retrieves people by id (\\d+)")
    public void a_user_retrieves_people_by_id(int id){
        RestAssuredConfig config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 8000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 8000));
        request = given().config(config);
        response = request.when().get(ENDPOINT_GET_PEOPLE_BY_ID+id+"/");
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("the status api status code is (\\d+)")
    public void verify_api_status_code(int statusCode){
        response.then().statusCode(statusCode);
    }


    @And("response body has a valid (.*) schema")
    public void response_equals(String schema){
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/"+schema+".json"));
    }

}
