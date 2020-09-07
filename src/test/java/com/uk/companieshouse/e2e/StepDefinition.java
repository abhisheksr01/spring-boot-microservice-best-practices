package com.uk.companieshouse.e2e;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition extends SpringIntegration {
    String crn = null;
    private ResponseEntity responseEntity;
    private int exceptionStatusCode;
    private String exceptionMessage;

    @Given("^Customer enters the Customer Reference Number \"([^\"]*)\"$")
    public void requesting_details_for(String crn) throws Throwable {
        this.crn = crn;
    }

    @When("^The customer makes a call to get the details$")
    public void an_api_call_is_being_made() throws Throwable {
        startService();
        stubGovCompaniesHouseExternalService();
        responseEntity = restTemplate.getForEntity
                (getDefaultURL() + "companieshouse/search/" + crn, List.class);
    }

    @Then("^The API should return the Companies House Details$")
    public void should_return_the_Companies_House_Details() throws Throwable {
        assertEquals(200, responseEntity.getStatusCodeValue());

        stopService();
    }

    @When("The customer makes a call with non existent CRN to get the details")
    public void the_customer_makes_a_call_with_non_existent_CRN_to_get_the_details() {
        startService();
        stubGovCompaniesHouseExternalServiceForNonExistentCRN();
        try {
            responseEntity = restTemplate.getForEntity
                    (getDefaultURL() + "companieshouse/search/" + crn, List.class);
        } catch (HttpClientErrorException exception) {
            exceptionStatusCode = exception.getRawStatusCode();
            exceptionMessage = exception.getResponseBodyAsString();
        }
    }

    @Then("The API should return response status code {int} and message {string}")
    public void the_API_should_return_response_status_code_and_message(Integer errorCode, String errorMessage) {
        assertEquals(errorCode, exceptionStatusCode);
        assertEquals(errorMessage, exceptionMessage);
        stopService();
    }
}
