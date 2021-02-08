Feature: Testing POST request

  Background: valid endpoint
    Given valid URI endpoint
    
  @APITest
  Scenario Outline: POST request for creating loan application
    
    When POST request is sent with valid "<username>", "<password>", token and payload
    Then Status code should be 200
    And Content Type is Json
    Then response first name is "<name>"
       * response product type is "<productType>"
       * response purpose is "<purpose>"

    Examples:

    |username                          |password    |name|productType  |purpose    |
    |coding.challenge.login@upgrade.com|On$3XcgsW#9q|Ian |PERSONAL_LOAN|CREDIT_CARD|




  @APITest
  Scenario Outline: POST request with invalid username

      When POST request is sent with invalid "<username>" in headers and payload
      Then Status code should be 401

      Examples:

      |username                                  |
      |invalid.coding.challenge.login@upgrade.com|

