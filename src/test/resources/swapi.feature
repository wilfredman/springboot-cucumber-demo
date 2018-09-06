Feature: Get people by ID
  Scenario: User calls web service to get people by ID
    When a user retrieves people by id 1
    Then the status api status code is 200
    And response body has a valid people schema
