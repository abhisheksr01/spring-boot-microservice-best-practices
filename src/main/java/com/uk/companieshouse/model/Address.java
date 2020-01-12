package com.uk.companieshouse.model;

import lombok.Data;

@Data
public class Address {
    private String postalCode;
    private String locality;
    private String addressLine1;
    private String premises;
}
