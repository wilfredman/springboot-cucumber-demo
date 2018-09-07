Feature: Get order details
    Scenario: client calls web service to get order by SKU
      When this client retrieves order by sku AB15426
      Then the order service status code is 200
      And order response includes the following
        | sku | AB15426 |
      And response body has a valid order schema
