package com.uk.companieshouse.e2e;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        plugin = {
                "pretty", "html:reports/cucumber", "json:reports/cucumber/cucumber.json"
        })
public class CucumberTest {
}
