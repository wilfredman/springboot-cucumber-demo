package com.tsukhu.demo.steps;

import com.tsukhu.demo.SpringIntegrationTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Value;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Ignore
public class SwapiSteps extends SpringIntegrationTest {

    @Value("${app.swapi.url}")
    private String baseURL;

    @Value("${app.endpoint.timeout}")
    private Integer timeOut;

    @When("this client retrieves people by id (\\d+)")
    public void the_client_retrieves_people_by_id(int id){
        RestAssuredConfig config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().
                setParam("http.connection.timeout",timeOut).
                setParam("http.socket.timeout",timeOut).
                setParam("http.connection-manager.timeout",timeOut));
        request = given().config(config);
        response = request.when().get(baseURL+id+"/");
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("the people service status code is (\\d+)")
    public void the_people_service_status_code_check(int statusCode){
        response.then().statusCode(statusCode);
    }


    @And("the people response body has a valid (.*) schema")
    public void response_equals(String schema){
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/"+schema+".json"));
    }

}
