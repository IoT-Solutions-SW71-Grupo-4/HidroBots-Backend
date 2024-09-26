Feature: Get Crop By ID

  Scenario: Retrieve a crop by its ID
    Given a crop is saved in the repository with id 1
    When the crop is requested by id
    Then the crop should be returned