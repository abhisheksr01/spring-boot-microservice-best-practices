package com.uk.companieshouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Item {

    @JsonProperty("description_identifier")
    private List<String> descriptionIdentifier;
    @JsonProperty("company_status")
    private String companyStatus;
    private String kind;
    @JsonProperty("company_type")
    private String companyType;
    private AddressGovUK address;
    private Links links;
    private String title;
    @JsonProperty("address_snippet")
    private String addressSnippet;
    @JsonProperty("date_of_creation")
    private String dateOfCreation;
    private String description;
    @JsonProperty("company_number")
    private String companyNumber;
    @JsonProperty("date_of_cessation")
    private String dateOfCessation;
}
