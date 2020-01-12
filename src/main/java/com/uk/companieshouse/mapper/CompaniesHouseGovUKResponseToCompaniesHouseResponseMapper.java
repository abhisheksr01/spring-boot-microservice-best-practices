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

    @Mapping(source = "companyStatus", target = "companyStatus")
    @Mapping(source = "companyType", target = "companyType")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "addressSnippet", target = "addressSnippet")
    @Mapping(source = "dateOfCreation", target = "dateOfCreation")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "companyNumber", target = "companyNumber")
    CompaniesHouseResponse mapItemToCompaniesHouseResponse(Item item);

    @Mapping(source = "postalCode", target = "postalCode")
    @Mapping(source = "addressLine1", target = "addressLine1")
    Address mapAddress(AddressGovUK addressGovUK);
}
