package com.uk.companieshouse.controller;

import com.uk.companieshouse.model.CompaniesHouseResponse;
import com.uk.companieshouse.service.CompaniesHouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompaniesHouseController {

    private CompaniesHouseService companiesHouseService;

    public CompaniesHouseController(CompaniesHouseService companiesHouseService) {
        this.companiesHouseService = companiesHouseService;
    }

    @GetMapping(value = "/getCompaniesHouse/{crn}")
    public List<CompaniesHouseResponse> getCompaniesHouseResponse(@PathVariable String crn) {
        return companiesHouseService.getCompaniesHouseResponse(crn);
    }
}
