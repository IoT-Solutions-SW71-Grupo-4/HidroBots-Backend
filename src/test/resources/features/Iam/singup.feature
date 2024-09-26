Feature: User Sign-Up

  Scenario: Successful user sign-up
    Given a new user with fullName "New User", email "new_user@example.com" and password "password123"
    When the user attempts to sign up
    Then the sign-up should be successful