package com.tsukhu.demo.steps;

import com.tsukhu.demo.SpringIntegrationTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.Matchers.is;

public class VersionSteps extends SpringIntegrationTest {

    @When("^the client calls /version$")
    public void the_client_issues_GET_version() {
        executeGet("http://localhost:8080/version");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) {
        latestResponse.then().statusCode(statusCode);
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) {
        latestResponse.then().assertThat().body(is(version));
    }
}
