Meta:

Narrative:
As a user
I want to sign in Mail.ru

Scenario: authentication with valid account
Given Account with valid data
When I sign in with valid account
Then I see my login in the header bar

Scenario: authentication with invalid account
Given Account with invalid data
When I sign in with invalid account
Then I see an error

Scenario: authentication with empty login or password
When I sign in without empty <login> <password>
Then I see an error

Examples:
|login|password|
|login||
||password|
