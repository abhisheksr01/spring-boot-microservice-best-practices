package com.uk.companieshouse.mapper;

import com.uk.companieshouse.model.Address;
import com.uk.companieshouse.model.CompaniesHouseResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.uk.companieshouse.mapper.CompaniesHouseGovUKResponseToCompaniesHouseResponseMapper.MAPPER;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseGovUKResponse;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseResponseList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CompaniesHouseGovUKResponseToCompaniesHouseResponseMapperTest {

    @Test
    void map_shouldMapToCompaniesHouseResponse_whenCompaniesHouseGovUKResponseIsPassed() {
        List<CompaniesHouseResponse> actualCompaniesHouseResponse =
                MAPPER.map(getCompaniesHouseGovUKResponse().getItems());

        assertEquals(getCompaniesHouseResponseList(), actualCompaniesHouseResponse);
    }

    @Test
    void map_whenInputIsNull_returnsNull() {
        List<CompaniesHouseResponse> actualCompaniesHouseResponse =
                MAPPER.map(null);

        assertNull(actualCompaniesHouseResponse);
    }

    @Test
    void mapItemToCompaniesHouseResponse_whenInputIsNull_returnsNull() {
        CompaniesHouseResponse actualCompaniesHouseResponse =
                MAPPER.mapItemToCompaniesHouseResponse(null);

        assertNull(actualCompaniesHouseResponse);
    }

    @Test
    void mapAddress_whenInputIsNull_returnsNull() {
        Address address =
                MAPPER.mapAddress(null);

        assertNull(address);
    }
}
