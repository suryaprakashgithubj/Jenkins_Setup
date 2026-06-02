Feature: Tutorials Ninja Functionality

Scenario: Verify login with valid credentials

Given user open tutorials ninja website
When user click on my account
And user click on login
And user enter email and password
Then user click login button
And user navigate to account page


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
| Surya     | Prakash  | surya9945461r1@gmail.com | 9876543210 | Surya123 |
| Arun      | Kumar    | arun991d24g4f12@gmail.com  | 9876543211 | Arun123  |


Scenario: Verify product search

Given user open home page
When user enter product in search box
And user click search button
Then searched product should display


Scenario: Verify user can add product to cart

Given user open tutorials ninja home
When user search product for cart
And user click add to cart button
Then product should be added to cart successfully


Scenario: Verify user can remove item from cart

Given user add product into cart
When user open shopping cart
And user remove cart item
Then cart item should be removed


Scenario: Verify user logout successfully

Given user login into tutorials ninja application
When user click my account dropdown
And user click logout option
Then user should logout successfully