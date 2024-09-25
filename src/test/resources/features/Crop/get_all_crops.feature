Feature: Get All Crops

  Scenario: Successfully retrieve all crops
    Given there are crops in the repository
    When all crops are requested
    Then all crops should be returned