Feature: Complete Booking Workflow

  @E2E
  Scenario: Validate Booking

    Given user launches browser

    When user enters "user@phptravels.com" and "demouser"

    And clicks login button

    And user enters booking destination

    And user selects booking checkin date

    And user selects booking checkout date

    And user selects booking travellers

    And clicks booking search button

    And user clicks book now

    And user confirms booking

    Then validate booking confirmation