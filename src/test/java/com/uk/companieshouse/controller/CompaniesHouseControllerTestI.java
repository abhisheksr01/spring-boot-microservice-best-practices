package com.uk.companieshouse.controller;

import com.uk.companieshouse.service.CompaniesHouseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static com.uk.companieshouse.utils.TestHelper.TESTCRN;
import static com.uk.companieshouse.utils.TestHelper.getCompaniesHouseResponseList;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompaniesHouseController.class)
class CompaniesHouseControllerTestI {

    @MockBean
    private CompaniesHouseService companiesHouseService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("When a call to '/search' URL is made with valid CRN should return a success response ")
    void getCompaniesHouseResponse_whenValidCRNIsPassed_shouldReturnCHResponse() throws Exception {
        final String EXPECTED_RESPONSE = "[{\"companyStatus\":\"dissolved\",\"companyType\":\"ltd\",\"address\":{\"postalCode\":\"W1U 2EL\",\"locality\":\"London\",\"addressLine1\":\"Bentinck Street\",\"premises\":\"9 \"},\"title\":\"123456789  LIMITED\",\"addressSnippet\":\"9  Bentinck Street, London, W1U 2EL\",\"dateOfCreation\":\"2012-10-04\",\"description\":\"08240241 - Dissolved on 23 December 2014\",\"companyNumber\":\"08240241\"}]";

        when(companiesHouseService.getCompaniesHouseResponse(TESTCRN)).
                thenReturn(getCompaniesHouseResponseList());

        this.mockMvc.perform(get("/search/" + TESTCRN)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(EXPECTED_RESPONSE)));
    }

}