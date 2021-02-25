package com.uk.companieshouse.e2e;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@Data
public abstract class SpringIntegration implements WireMockService {

    protected RestTemplate restTemplate = new RestTemplate();
    @Value("${defaultURL}")
    private String defaultURL;

}
