Feature: verify Registration functionality

Scenario Outline: verify user registration with multiple data

Given registration page should be open in default browser
When enter firstname "<firstname>"
And enter lastname "<lastname>"
And enter email "<email>"
And enter password "<password>"
And click on register button
Then registration status should be "<status>"

Examples:
| firstname | lastname | email                | password    | status  |
| Surya     | Prakash  | surya@gmail.com      | Surya123    | valid   |
| Arun      | Kumar    | arun@gmail.com       | Arun123     | valid   |
| Test      | User     | invalidmail          | test123     | invalid |
| Meena     | Lakshmi  | meena@gmail.com      |             | invalid |