Feature: Return Customers

  Background:
    Given url baseUrl
    Given path '/customers'

  Scenario: get list of all customers
 
    When method GET
    Then status 302
 
