Feature: Registration Functionality

Scenario Outline: Verify user registration

Given user open registration page

When user enter firstname <firstname>

And user enter lastname <lastname>

And user enter register email <email>

And user enter telephone <telephone>

And user enter password <password>

Then user click continue button

And account should be created successfully


Examples:
| firstname | lastname | email               | telephone  | password |
| Surya | Prakash | surya5243443sd4ec355gh51@gmail.com | 9876543210 | Surya123 |
| Arun  | Kumar   | arun5sdsg4g42dr4c5r552@gmail.com  | 9876543211 | Arun123  |