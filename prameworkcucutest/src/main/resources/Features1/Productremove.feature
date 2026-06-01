@Smoke

Feature: Remove Cart Item

Scenario: Verify user can remove item from cart

Given user add product into cart

When user open shopping cart

And user remove cart item

Then cart item should be removed