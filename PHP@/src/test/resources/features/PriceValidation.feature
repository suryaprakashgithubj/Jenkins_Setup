Feature: Hotel Price Validation

  @Regression
  Scenario: Validate Hotel Prices
    Given user launches browser
    When user searches hotels
    Then fetch all hotel prices
    And print highest hotel price
    And print lowest hotel price
    And print average hotel price
    And check duplicate hotel names
