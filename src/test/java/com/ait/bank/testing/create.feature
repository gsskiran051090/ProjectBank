Feature: Create customer

 Background:
    * url baseUrl
    * def addCustomer = '/addCustomers'
    * def fetchCustomer = '/customers/'

 Scenario: Create a customer
 
    Given path addCustomer
    And request { id:4,customer_address: 'Hyderabad', customer_email: 'kiran@gmail.com',customer_name: 'kiran' }
    And header Accept = 'application/json'
    When method post
    Then status 201
