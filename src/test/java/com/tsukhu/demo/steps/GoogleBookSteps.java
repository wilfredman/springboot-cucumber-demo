package com.tsukhu.demo.steps;

import com.tsukhu.demo.SpringIntegrationTest;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

@Ignore
public class GoogleBookSteps extends SpringIntegrationTest {

    @Value("${app.googlebooks.url}")
    private String baseURL;

    @Before
    public void setUp() {
        config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().
                setParam("http.connection.timeout",timeOut).
                setParam("http.socket.timeout",timeOut).
                setParam("http.connection-manager.timeout",timeOut));
    }

    @Given("a book exists with an isbn of (.*)")
    public void a_book_exists_with_isbn(String isbn){
        request = given().config(config).param("q", "isbn:" + isbn);
    }

    @When("the client retrieves the book by isbn")
    public void the_client_retrieves_the_book_by_isbn(){
        response = request.when().get(baseURL);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("the book service status code is (\\d+)")
    public void the_book_service_code_check(int statusCode){
        json = response.then().statusCode(statusCode);
    }

    @And("book response includes the following$")
    public void book_response_equals(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }

    @And("book response includes the following in any order")
    public void book_response_contains_in_any_order(Map<String,String> responseFields){
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
