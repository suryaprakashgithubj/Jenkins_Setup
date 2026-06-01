Feature: PHPTravels Login Module

  @Smoke @Regression
  Scenario Outline: Validate Login Functionality
    Given user launches browser
    When user enters "<username>" and "<password>"
    And clicks login button
    Then validate login result "<username>"

    Examples:
      | username                  | password  |
      | user@phptravels.com       | demouser  |
      | invalid@gmail.com         | invalid   |
      |                           | demouser  |
      | user@phptravels.com       |           |
