# springboot-cucumber-demo

SpringBoot based integration testing using Cucumber and Rest Assured

## Objective

* Create a reference implementation which provides the best practices of running integration & component tests in a SpringBoot application
* While Rest Assured provides an excellent DSL to quickly create component and integration tests , using Cucumber will provide us a not only creating readable and maintainable code , but also being able communicate the requirements across various stakeholdes in a human readable language.
* Gherkin (used by Cucumber to define the automated tests) along with Rest Assured provides a very effective solution for the same.
* For Component level tests - we can mock the external apis 
* For Integration level tests - a different provide can be used to use actual APIs rather than mocks

### Technologies

* Spring Boot Test - Provides the spring boot ecosystem required for the tests
* Rest Assured - Provides the service client APIs using the powerful DSL for creating maintainable tests
* Rest Assured JSON Schema Validator - Response Schema Validation
* Hamcrest - Assertions
* Swagger Request Validator for Rest Assured (swagger-request-validator-restassured) - Use Rest Assured to validate the API response using the swagger spec (TBD)
* Cucumber (with Spring Boot) - BDD based Integration Test and Component Tests

### Running all the tests
```cmd
mvn clean install -Pintegration
```
