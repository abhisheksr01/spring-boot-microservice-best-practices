package com.uk.companieshouse.service;

import com.uk.companieshouse.connector.CompaniesHouseConnector;
import com.uk.companieshouse.model.CompaniesHouseGovUKResponse;
import com.uk.companieshouse.model.CompaniesHouseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.uk.companieshouse.mapper.CompaniesHouseGovUKResponseToCompaniesHouseResponseMapper.MAPPER;

@Service
public class CompaniesHouseService {

    private CompaniesHouseConnector companiesHouseConnector;

    public CompaniesHouseService(CompaniesHouseConnector companiesHouseService) {
        this.companiesHouseConnector = companiesHouseService;
    }

    public List<CompaniesHouseResponse> getCompaniesHouseResponse(String crn) {
        CompaniesHouseGovUKResponse companiesHouseGovUKResponse = companiesHouseConnector.getCompaniesHouseDetails(crn);

        List<CompaniesHouseResponse> companiesHouseResponses = MAPPER.map(companiesHouseGovUKResponse.getItems());

        return companiesHouseResponses;
    }
}
