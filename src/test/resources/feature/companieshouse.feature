Feature: Pass the Companies House Number & get the Companies Details

  Scenario: When a CRN is passed the API should return the Company Details
    Given Customer enters the Customer Reference Number "111111111"
    When The customer makes a call to get the details
    Then The API should return the Companies House Details