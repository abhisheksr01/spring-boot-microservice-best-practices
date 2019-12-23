package com.uk.companieshouse.model;

import lombok.Data;

@Data
public class AddressGovUK {
    private String postal_code;
    private String locality;
    private String address_line_1;
    private String premises;
}