package com.uk.companieshouse.connector;

import com.uk.companieshouse.model.CompaniesHouseGovUKResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Component
public class CompaniesHouseConnector {

    private final String authUsername;
    private final String govCompaniesHouseEndpoint;
    private final RestTemplate restTemplate;

    public CompaniesHouseConnector(@Value("${govCompaniesHouse.endpoint}") String govCompaniesHouseEndpoint,
                                   @Value("${govCompaniesHouse.authUserName}") String authUsername,
                                   RestTemplate restTemplate) {
        this.govCompaniesHouseEndpoint = govCompaniesHouseEndpoint;
        this.authUsername = authUsername;
        this.restTemplate = restTemplate;
    }

    public CompaniesHouseGovUKResponse getCompaniesHouseDetails(String crn) {
        log.info("CompaniesHouseConnector:getCompaniesHouseDetails: Init...");
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor(authUsername, ""));

        log.debug("CompaniesHouseConnector:getCompaniesHouseDetails: Make External call to: {} with CRN: {}",
                govCompaniesHouseEndpoint, crn);
        ResponseEntity<CompaniesHouseGovUKResponse> responseEntity = restTemplate
                .getForEntity(govCompaniesHouseEndpoint, CompaniesHouseGovUKResponse.class, crn);

        CompaniesHouseGovUKResponse companiesHouseGovUKResponse = responseEntity.getBody();
        if (isEmpty(companiesHouseGovUKResponse.getItems())) {
            log.error("CompaniesHouseConnector:getCompaniesHouseDetails: Throw CRN Not Found Exception");
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "CRN not found");
        }

        log.info("CompaniesHouseConnector:getCompaniesHouseDetails: End...");
        return companiesHouseGovUKResponse;
    }
}
