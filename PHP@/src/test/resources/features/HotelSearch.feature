Feature: PHPTravels Hotel Search

  @Smoke
  Scenario: Validate Hotel Search
    Given user launches browser
    When user enters destination
    And user selects checkin date
    And user selects checkout date
    And user selects travellers
    And clicks search button
    Then validate hotel results displayed
