Feature: Delete Crop

  Scenario: Delete a crop by its ID
    Given a crop is saved in the repository with id 1
    When the crop is deleted by id
    Then the crop should no longer exist in the repository