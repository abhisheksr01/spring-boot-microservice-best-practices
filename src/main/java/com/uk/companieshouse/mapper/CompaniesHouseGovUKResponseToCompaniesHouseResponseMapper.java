package com.uk.companieshouse.mapper;

import com.uk.companieshouse.model.Address;
import com.uk.companieshouse.model.AddressGovUK;
import com.uk.companieshouse.model.CompaniesHouseResponse;
import com.uk.companieshouse.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CompaniesHouseGovUKResponseToCompaniesHouseResponseMapper {

    CompaniesHouseGovUKResponseToCompaniesHouseResponseMapper MAPPER =
            Mappers.getMapper(CompaniesHouseGovUKResponseToCompaniesHouseResponseMapper.class);

    List<CompaniesHouseResponse> map(List<Item> itemList);

    @Mapping(source = "company_status", target = "companyStatus")
    @Mapping(source = "company_type", target = "companyType")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "address_snippet", target = "addressSnippet")
    @Mapping(source = "date_of_creation", target = "dateOfCreation")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "company_number", target = "companyNumber")
    CompaniesHouseResponse mapItemToCompaniesHouseResponse(Item item);

    @Mapping(source = "postal_code", target = "postalCode")
    @Mapping(source = "address_line_1", target = "addressLine1")
    Address mapAddress(AddressGovUK addressGovUK);
}
