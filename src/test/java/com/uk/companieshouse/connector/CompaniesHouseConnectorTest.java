package com.uk.companieshouse.connector;

import com.uk.companieshouse.model.CompaniesHouseGovUKResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.uk.companieshouse.utils.TestHelper.TEST_CRN;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseGovUKResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompaniesHouseConnectorTest {

    private static final String DUMMY_COMPANIES_HOUSE_GOV_ENDPOINT = "https://mock.companieshouse.gov.uk/search/companies?q=";
    private static final String AUTH_USERNAME = "dummy-auth-user";
    private CompaniesHouseConnector companiesHouseConnector;
    private RestTemplate mockRestTemplate;

    @BeforeEach
    void setUp() {
        mockRestTemplate = mock(RestTemplate.class);
        companiesHouseConnector =
                new CompaniesHouseConnector(DUMMY_COMPANIES_HOUSE_GOV_ENDPOINT, AUTH_USERNAME, mockRestTemplate);
    }

    @Test
    void getCompaniesHouseDetails_shouldReturnCompaniesHouseDetails_whenExternalAPICallIsMade() {
        when(mockRestTemplate
                .getForEntity(DUMMY_COMPANIES_HOUSE_GOV_ENDPOINT, CompaniesHouseGovUKResponse.class, TEST_CRN))
                .thenReturn(new ResponseEntity<>(getCompaniesHouseGovUKResponse(), HttpStatus.OK));

        CompaniesHouseGovUKResponse actualCompaniesHouseDetails =
                companiesHouseConnector.getCompaniesHouseDetails(TEST_CRN);

        assertEquals(getCompaniesHouseGovUKResponse(), actualCompaniesHouseDetails);
    }
}