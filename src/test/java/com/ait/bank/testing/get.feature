Feature: Get request test

  Background:
    * url baseUrl
    * def personBase = '/customers/'

  Scenario: Trying to fetch non-existant record

    Given path personBase + '8'
    When method GET
    Then status 302
    
    Scenario: Trying to fetch record

    Given path personBase + '4'
    When method GET
    Then status 302
    
    