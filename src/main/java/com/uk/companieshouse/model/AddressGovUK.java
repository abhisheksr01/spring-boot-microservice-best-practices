package com.uk.companieshouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressGovUK {
    @JsonProperty("postal_code")
    private String postalCode;
    private String locality;
    @JsonProperty("address_line_1")
    private String addressLine1;
    private String premises;
}
