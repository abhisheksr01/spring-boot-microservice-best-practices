package com.uk.companieshouse.service;

import com.uk.companieshouse.connector.CompaniesHouseConnector;
import com.uk.companieshouse.model.CompaniesHouseGovUKResponse;
import com.uk.companieshouse.model.CompaniesHouseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.uk.companieshouse.mapper.CompaniesHouseGovUKResponseToCompaniesHouseResponseMapper.MAPPER;

@Slf4j
@Service
public class CompaniesHouseService {

    private final CompaniesHouseConnector companiesHouseConnector;

    public CompaniesHouseService(CompaniesHouseConnector companiesHouseService) {
        this.companiesHouseConnector = companiesHouseService;
    }

    public List<CompaniesHouseResponse> getCompaniesHouseResponse(String crn) {
        log.info("CompaniesHouseService:getCompaniesHouseResponse:Init...");

        CompaniesHouseGovUKResponse companiesHouseGovUKResponse = companiesHouseConnector.getCompaniesHouseDetails(crn);
        log.debug("CompaniesHouseService:getCompaniesHouseResponse: For CRN : {}, Response from CH GOV UK : {}",
                crn, companiesHouseGovUKResponse);

        List<CompaniesHouseResponse> companiesHouseResponses = MAPPER.map(companiesHouseGovUKResponse.getItems());
        log.debug("CompaniesHouseService:getCompaniesHouseResponse: For CRN : {}, Sending Filtered Response : {}",
                crn, companiesHouseResponses);

        log.info("CompaniesHouseService:getCompaniesHouseResponse:End...");
        return companiesHouseResponses;
    }
}
