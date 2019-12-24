package com.uk.companieshouse.connector;

import com.uk.companieshouse.model.CompaniesHouseGovUKResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class CompaniesHouseConnector {

    private String authUsername;
    private String govCompaniesHouseEndpoint;
    @Autowired
    private RestTemplate restTemplate;

    public CompaniesHouseConnector(@Value("${govCompaniesHouse.endpoint}") String govCompaniesHouseEndpoint,
                                   @Value("${govCompaniesHouse.authUserName}") String authUsername,
                                   RestTemplate restTemplate) {
        this.govCompaniesHouseEndpoint = govCompaniesHouseEndpoint;
        this.authUsername = authUsername;
        this.restTemplate = restTemplate;
    }

    public CompaniesHouseGovUKResponse getCompaniesHouseDetails(String crn) {
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor(authUsername, ""));
        ResponseEntity<CompaniesHouseGovUKResponse> responseEntity = restTemplate
                .getForEntity(govCompaniesHouseEndpoint, CompaniesHouseGovUKResponse.class, crn);
        CompaniesHouseGovUKResponse companiesHouseGovUKResponse = responseEntity.getBody();
        if (isEmpty(companiesHouseGovUKResponse.getItems())) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "CRN not found");
        }
        return companiesHouseGovUKResponse;
    }
}
