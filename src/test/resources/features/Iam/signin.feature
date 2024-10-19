Feature: User Sign-In

  Scenario: Successful user sign-in
    Given an existing user with email "existing_user@example.com" and password "password123"
    When the user attempts to sign in
    Then the sign-in should be successful