package com.uk.companieshouse.mapper;

import com.uk.companieshouse.model.CompaniesHouseResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.uk.companieshouse.mapper.CompaniesHouseGovUKResponseToCompaniesHouseResponseMapper.MAPPER;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseGovUKResponse;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseResponseList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompaniesHouseGovUKResponseToCompaniesHouseResponseMapperTest {

    @Test
    void map_shouldMapToCompaniesHouseResponse_whenCompaniesHouseGovUKResponseIsPassed() {
        List<CompaniesHouseResponse> actualCompaniesHouseResponse =
                MAPPER.map(getCompaniesHouseGovUKResponse().getItems());

        assertEquals(getCompaniesHouseResponseList(), actualCompaniesHouseResponse);
    }
}