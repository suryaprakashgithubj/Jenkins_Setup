Feature: verify Login functionality

Scenario Outline: verify user is able to login with multiple credentials

Given login page should be open in default browser
When click on username field and add valid user "<username>"
And then click on password field and enter valid "<password>"
And now click on submit button
Then login status should be "<status>"

Examples:
| username | password    | status  |
| student  | Password123 | valid   |
| admin    | admin123    | invalid |
| surya    | surya123    | invalid |
| test     | test123     | invalid |