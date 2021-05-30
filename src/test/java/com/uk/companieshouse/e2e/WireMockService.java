package com.uk.companieshouse.e2e;

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

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.uk.companieshouse.utils.TestHelper.TESTCRN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface WireMockService {

    @Rule
    WireMockRule WIRE_MOCK_RULE = new WireMockRule(WireMockConfiguration.wireMockConfig().port(8005));

    default void stubGovCompaniesHouseExternalService() {
        configureFor("localhost", 8005);
        givenThat(get(urlPathMatching("/search/companies"))
                .withBasicAuth("dummy-auth-user", "")
                .withQueryParam("q", equalTo(TESTCRN))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBodyFile("companies-house-gov-UK-response.json")
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("dummy-auth-user", ""));
        ResponseEntity<CompaniesHouseGovUKResponse> response = restTemplate.getForEntity
                ("http://localhost:8005//search/companies?q={crn}", CompaniesHouseGovUKResponse.class, TESTCRN);
        assertEquals(200, response.getStatusCodeValue());
    }

    default void stubGovCompaniesHouseExternalServiceForNonExistentCRN() {
        configureFor("localhost", 8005);
        givenThat(get(urlPathMatching("/search/companies"))
                .withBasicAuth("dummy-auth-user", "")
                .withQueryParam("q", equalTo("222222222"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBodyFile("companies-house-gov-UK-response-crn-404.json")
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("dummy-auth-user", ""));
        ResponseEntity<CompaniesHouseGovUKResponse> response = restTemplate.getForEntity
                ("http://localhost:8005//search/companies?q={crn}",
                        CompaniesHouseGovUKResponse.class, "222222222");
        assertEquals(200, response.getStatusCodeValue());
    }

    default void startService() {
        WIRE_MOCK_RULE.start();
    }

    default void stopService() {
        WIRE_MOCK_RULE.stop();
    }
}
