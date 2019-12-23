package com.uk.companieshouse.model;

import lombok.Data;

import java.util.List;

@Data
public class CompaniesHouseGovUKResponse {
    private String page_number;
    private String kind;
    private Integer start_index;
    private Integer items_per_page;
    private Integer total_results;
    private List<Item> items;
}
