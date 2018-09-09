Feature: Get order details

  Scenario: client calls web service to get order by SKU
    When this client retrieves order by sku AB15426
    Then the order service status code is 200
    And order response includes the following
      | sku | AB15426 |
    And response body has a valid order schema

  Scenario Outline: order sku checks
    When this client retrieves order by sku <sku>
    Then the order service status code is <status>
    And order response includes the following
      | sku | <sku> |
    And response body has a valid <schema> schema

    Examples:
      | sku     | status  | schema |
      | AB-2123 | 200     | order  |
      | FF-8920 | 200     | order  |
      | FF-322s | 200     | order  |
