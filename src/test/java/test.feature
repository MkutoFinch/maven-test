Given I visit "/login"
When I enter "Bob" in the "user name" field
And I enter "tester" in the "password" field
And I press the "login" button
Then I should see the "welcome" page