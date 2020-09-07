Feature: Pass the Companies House Number & get the Companies Details

  Scenario: When a CRN is passed then API should return the Company Details
    Given Customer enters the Customer Reference Number "08240241"
    When The customer makes a call to get the details
    Then The API should return the Companies House Details

  Scenario: When a CRN is passed & do not exist, API should return status code 404
    Given Customer enters the Customer Reference Number "222222222"
    When The customer makes a call with non existent CRN to get the details
    Then The API should return response status code 404 and message "CRN not found"