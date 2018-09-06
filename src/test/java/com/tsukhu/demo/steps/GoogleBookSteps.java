package com.tsukhu.demo.steps;

import com.tsukhu.demo.SpringIntegrationTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.params.CoreConnectionPNames;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

public class GoogleBookSteps extends SpringIntegrationTest {

    private String ENDPOINT_GET_BOOK_BY_ISBN = "https://www.googleapis.com/books/v1/volumes";

    @Given("a book exists with an isbn of (.*)")
    public void a_book_exists_with_isbn(String isbn){
        RestAssuredConfig config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 8000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 8000));
        request = given().config(config).param("q", "isbn:" + isbn);
    }

    @When("a user retrieves the book by isbn")
    public void a_user_retrieves_the_book_by_isbn(){
        response = request.when().get(ENDPOINT_GET_BOOK_BY_ISBN);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("the status code is (\\d+)")
    public void verify_status_code(int statusCode){
        json = response.then().statusCode(statusCode);
    }

    @And("response includes the following$")
    public void response_equals(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }

    @And("response includes the following in any order")
    public void response_contains_in_any_order(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), containsInAnyOrder(field.getValue()));
            }
        }
    }
}
