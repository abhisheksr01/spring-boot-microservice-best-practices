package com.uk.companieshouse.model;

import lombok.Data;

@Data
public class CompaniesHouseResponse {
    private String companyStatus;
    private String companyType;
    private Address address;
    private String title;
    private String addressSnippet;
    private String dateOfCreation;
    private String description;
    private String companyNumber;
}
