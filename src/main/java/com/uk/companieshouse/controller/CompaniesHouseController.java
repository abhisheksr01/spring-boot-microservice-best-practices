package com.uk.companieshouse.controller;

import com.uk.companieshouse.model.CompaniesHouseResponse;
import com.uk.companieshouse.service.CompaniesHouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@RestController
public class CompaniesHouseController {

    private final CompaniesHouseService companiesHouseService;

    public CompaniesHouseController(CompaniesHouseService companiesHouseService) {
        this.companiesHouseService = companiesHouseService;
    }

    @GetMapping(value = "/search/{crn}")
    public List<CompaniesHouseResponse> getCompaniesHouseResponse(@PathVariable String crn) {
        log.info("CompaniesHouseController:getCompaniesHouseResponse:Init...");
        if (!crn.matches("^[a-z\\d\\s]+$")) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "CRN should only contain alphanumeric characters");
        }
        return companiesHouseService.getCompaniesHouseResponse(crn);
    }
}
