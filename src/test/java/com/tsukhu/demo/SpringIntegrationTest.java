package com.tsukhu.demo;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public abstract class SpringIntegrationTest {
    protected Response latestResponse;

    protected RequestSpecification request;
    protected Response response;
    protected ValidatableResponse json;

    /**
     * Initial Setup
     */
    @Before
    public void setUp() {
        request = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    protected void executeGet(String url) {
        request = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
        latestResponse = given().spec(request).when().get(url);

    }


}
