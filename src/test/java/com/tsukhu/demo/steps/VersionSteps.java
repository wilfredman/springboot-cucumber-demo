package com.tsukhu.demo.steps;

import com.tsukhu.demo.SpringIntegrationTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Ignore;

import static org.hamcrest.Matchers.is;

@Ignore
public class VersionSteps extends SpringIntegrationTest {

    @When("^the client receives server version$")
    public void the_client_receives_server_version() {
        executeGet("http://localhost:8080/version");
    }

    @Then("^the version service status code is (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) {
        latestResponse.then().statusCode(statusCode);
    }

    @And("^the version service response body contains (.+)$")
    public void the_client_receives_server_version_body(String version) {
        latestResponse.then().assertThat().body(is(version));
    }
}
