Feature: verify Search feature
Scenario: verify user is able to search the product and relevant products appear
Given login page should be open in default browser
When click on username field and add valid user username
And then click on password button and enter valid password
And now click on submit button
And click on search field and add inputs
Then list of products

