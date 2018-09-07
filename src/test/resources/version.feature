Feature: the version can be retrieved
    Scenario: client makes call to GET /version
      When the client receives server version
      Then the version service status code is 200
      And the version service response body contains 1.0
