package com.uk.companieshouse.e2e;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinition extends SpringIntegration {
    String crn = null;
    private ResponseEntity responseEntity;

    @Given("^Customer enters the Customer Reference Number \"([^\"]*)\"$")
    public void requesting_details_for(String crn) throws Throwable {
        this.crn = crn;
    }

    @When("^The customer makes a call to get the details$")
    public void an_api_call_is_being_made() throws Throwable {
        startService();
        stubGovCompaniesHouseExternalService();
        responseEntity = restTemplate.getForEntity
                (DEFAULT_URL + "/getCompaniesHouse/" + crn, List.class);
    }

    @Then("^The API should return the Companies House Details$")
    public void should_return_the_Companies_House_Details() throws Throwable {
        assertEquals(200, responseEntity.getStatusCodeValue());

        stopService();
    }
}
