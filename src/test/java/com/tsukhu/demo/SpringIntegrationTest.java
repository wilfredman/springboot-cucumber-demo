package com.tsukhu.demo;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ContextConfiguration;

import static io.restassured.RestAssured.given;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
@AutoConfigureWireMock(port = 8090)
public abstract class SpringIntegrationTest {

    protected RequestSpecification request;
    protected Response response;
    protected ValidatableResponse json;
    protected RestAssuredConfig config;

    @Value("${app.endpoint.timeout}")
    protected Integer timeOut;


    @Value("${spring.profiles.active}")
    protected String activeProfile;

    protected void executeGet(String uri,String path) {
        request = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
        response = given().baseUri(uri).spec(request).when().get(path);

    }


}
