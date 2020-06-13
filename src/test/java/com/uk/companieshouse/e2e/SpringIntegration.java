package com.uk.companieshouse.e2e;

import org.springframework.web.client.RestTemplate;

public abstract class SpringIntegration implements com.uk.companieshouse.e2e.WireMockService {
    protected static final String DEFAULT_URL = "http://localhost:8081/";
    protected RestTemplate restTemplate = new RestTemplate();
}
