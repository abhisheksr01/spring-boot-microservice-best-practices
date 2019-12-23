package com.uk.companieshouse.e2e;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.uk.companieshouse.model.CompaniesHouseGovUKResponse;
import org.junit.Rule;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.uk.companieshouse.utils.TestHelper.TEST_CRN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface WireMockService {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.wireMockConfig().port(8005));

    RestTemplate restTemplate = new RestTemplate();

    default void stubGovCompaniesHouseExternalService() {
        configureFor("localhost", 8005);
        givenThat(get(urlPathMatching("/search/companies"))
                .withBasicAuth("dummy-auth-user", "")
                .withQueryParam("q", WireMock.equalTo(TEST_CRN))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBodyFile("companies-house-gov-UK-response.json")
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("dummy-auth-user", ""));
        ResponseEntity<CompaniesHouseGovUKResponse> response = restTemplate.getForEntity
                ("http://localhost:8005//search/companies?q={crn}", CompaniesHouseGovUKResponse.class, TEST_CRN);
        assertEquals(200, response.getStatusCodeValue());
    }

    default void startService() {
        wireMockRule.start();
    }

    default void stopService() {
        wireMockRule.stop();
    }
}
