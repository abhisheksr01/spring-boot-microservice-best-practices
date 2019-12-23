package com.uk.companieshouse.model;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private List<String> description_identifier;
    private String company_status;
    private String kind;
    private String company_type;
    private AddressGovUK address;
    private Links links;
    private String title;
    private String address_snippet;
    private String date_of_creation;
    private String description;
    private String company_number;
}
