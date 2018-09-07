package com.tsukhu.demo.steps;

import com.tsukhu.demo.SpringIntegrationTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@Ignore
public class OrderSteps extends SpringIntegrationTest {

    @When("this client retrieves order by sku (.*)")
    public void the_client_retrieves_order_by_sku(String skuCode){
        executeGet("http://localhost:8080/order/"+skuCode+"/");
    }

    @Then("the order service status code is (\\d+)")
    public void the_order_service_status_code_check(int statusCode){
        json = response.then().statusCode(statusCode);
    }

    @And("response body has a valid (.*) schema")
    public void order_response_equals(String schema){
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/"+schema+".json"));
    }

    @And("order response includes the following$")
    public void order_response_includes(Map<String,String> responseFields){
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }
}
