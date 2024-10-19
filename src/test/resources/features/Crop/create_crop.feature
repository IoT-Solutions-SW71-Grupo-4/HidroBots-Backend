Feature: Create Crop

  Scenario: Successfully create a new crop
    Given a crop with name "Tomato" and irrigation type "Manual"
    When the crop is created
    Then the crop should be saved in the repository
    And the crop id should be returned