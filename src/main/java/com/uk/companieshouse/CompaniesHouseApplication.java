package com.uk.companieshouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CompaniesHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompaniesHouseApplication.class, args);
    }
}
