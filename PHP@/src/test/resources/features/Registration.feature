Feature: PHPTravels Registration Module

  @Regression
  Scenario: Validate Registration
    Given user launches browser
    When user enters first name
    And user enters last name
    And user enters phone number
    And user enters email
    And user enters password
    And user selects country
    And clicks signup button
    Then validate registration success
