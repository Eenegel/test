Meta:

Narrative:
As a user
I want to do some actions with letter

Lifecycle:
Before:
Given I signed in Mail.ru
And Open 'New Message' page

Scenario: Create draft mail, delete it
Given Create draft and delete it
And Delete it from trash
When I seek for the draft in trash
Then I see 'Not found' result

Scenario: Create and send a new message with full fields correctly filled in
Given Fill in all fields
When I click 'send letter'
Then The letter should appear in Inbox and Sent

Scenario: Create new msg with wrong or empty address
Given Fill in all fields except 'To'
When I click 'send letter'
Then Error should be on screen

Scenario: Create new message without body and subject
Given Fill in only 'To'
When I click 'send letter'
Then Alert should be displayed and accepted
And Letter without subject should be in Inbox and Sent


