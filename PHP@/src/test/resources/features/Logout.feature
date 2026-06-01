Feature: Logout Module

  @Smoke
  Scenario: Validate Logout
    Given user launches browser
    When user enters "user@phptravels.com" and "demouser"
    And clicks login button
    And user clicks profile
    And user clicks logout
    Then validate logout
