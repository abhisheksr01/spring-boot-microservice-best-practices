package com.uk.companieshouse.connector;

import com.uk.companieshouse.e2e.WireMockService;
import com.uk.companieshouse.model.CompaniesHouseGovUKResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static com.uk.companieshouse.utils.TestHelper.TESTCRN;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseGovUKResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ActiveProfiles("test")
class CompaniesHouseConnectorTestI implements WireMockService {

    @Value("${govCompaniesHouse.endpoint}")
    private String govCompaniesHouseEndpoint;
    @Value("${govCompaniesHouse.authUserName}")
    private String authUsername;
    @Autowired
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        startService();
        stubGovCompaniesHouseExternalService();
    }

    @AfterEach
    void tearDown() {
        stopService();
    }

    @Test
    void getCompaniesHouseDetails_shouldReturnCHResponse_whenExecutedInSpringContext() {
        CompaniesHouseConnector companiesHouseConnector =
                new CompaniesHouseConnector(govCompaniesHouseEndpoint, authUsername, restTemplate);

        CompaniesHouseGovUKResponse actualCompaniesHouseGovUKResponse =
                companiesHouseConnector.getCompaniesHouseDetails(TESTCRN);

        assertEquals(getCompaniesHouseGovUKResponse(), actualCompaniesHouseGovUKResponse);
    }
}
