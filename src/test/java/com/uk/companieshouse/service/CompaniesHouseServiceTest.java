package com.uk.companieshouse.service;

import com.uk.companieshouse.connector.CompaniesHouseConnector;
import com.uk.companieshouse.model.CompaniesHouseGovUKResponse;
import com.uk.companieshouse.model.CompaniesHouseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.uk.companieshouse.utils.TestHelper.TESTCRN;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseGovUKResponse;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseResponseList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompaniesHouseServiceTest {

    private CompaniesHouseService companiesHouseService;
    private CompaniesHouseConnector mockCompaniesHouseConnector;
    private final CompaniesHouseGovUKResponse companiesHouseGovUKResponse = getCompaniesHouseGovUKResponse();
    private final List<CompaniesHouseResponse> expectedCompaniesHouseResponse = getCompaniesHouseResponseList();

    @BeforeEach
    void setUp() {
        mockCompaniesHouseConnector = mock(CompaniesHouseConnector.class);
        companiesHouseService = new CompaniesHouseService(mockCompaniesHouseConnector);
    }

    @Test
    void getCompaniesHouseResponse_shouldReturnCompaniesHouseResponse_whenCRNIsPassed() {
        when(mockCompaniesHouseConnector.getCompaniesHouseDetails(TESTCRN)).thenReturn(companiesHouseGovUKResponse);

        List<CompaniesHouseResponse> actualCompaniesHouseResponse =
                companiesHouseService.getCompaniesHouseResponse(TESTCRN);

        assertEquals(expectedCompaniesHouseResponse, actualCompaniesHouseResponse);
    }
}
