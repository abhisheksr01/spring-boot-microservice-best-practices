package com.uk.companieshouse.connector;

import com.uk.companieshouse.model.CompaniesHouseGovUKResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.uk.companieshouse.utils.TestHelper.TESTCRN;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseGovUKResponse;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseGovUKResponseCRNNotExist;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompaniesHouseConnectorTest {

    private static final String DUMMY_COMPANIES_HOUSE_GOV_ENDPOINT
            = "https://mock.companieshouse.gov.uk/search/companies?q=";
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
                .getForEntity(DUMMY_COMPANIES_HOUSE_GOV_ENDPOINT, CompaniesHouseGovUKResponse.class, TESTCRN))
                .thenReturn(new ResponseEntity<>(getCompaniesHouseGovUKResponse(), HttpStatus.OK));

        CompaniesHouseGovUKResponse actualCompaniesHouseDetails =
                companiesHouseConnector.getCompaniesHouseDetails(TESTCRN);

        assertEquals(getCompaniesHouseGovUKResponse(), actualCompaniesHouseDetails);
    }

    @Test
    void getCompaniesHouseDetails_shouldThrowException_whenItemsIsNullOrEmpty() {
        ResponseEntity<CompaniesHouseGovUKResponse> mockResponseEntity = mock(ResponseEntity.class);
        when(mockRestTemplate
                .getForEntity(DUMMY_COMPANIES_HOUSE_GOV_ENDPOINT,
                        CompaniesHouseGovUKResponse.class, "222222222"))
                .thenReturn(mockResponseEntity);
        when(mockResponseEntity.getBody()).thenReturn(getCompaniesHouseGovUKResponseCRNNotExist());

        assertThrows(HttpClientErrorException.class, () -> {
            companiesHouseConnector.getCompaniesHouseDetails("222222222");
        });
    }
}
