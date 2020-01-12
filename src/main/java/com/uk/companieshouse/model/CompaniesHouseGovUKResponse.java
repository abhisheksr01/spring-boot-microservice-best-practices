package com.uk.companieshouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CompaniesHouseGovUKResponse {
    @JsonProperty("page_number")
    private String pageNumber;
    private String kind;
    @JsonProperty("start_index")
    private Integer startIndex;
    @JsonProperty("items_per_page")
    private Integer itemsPerPage;
    @JsonProperty("total_results")
    private Integer totalResults;
    private List<Item> items;
}
