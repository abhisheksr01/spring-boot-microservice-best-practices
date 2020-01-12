package com.uk.companieshouse;

import org.junit.jupiter.api.Test;

class CompaniesHouseApplicationTest {

    @Test
    void main_contextLoad() {
        System.setProperty("server.port", "8082");
        CompaniesHouseApplication.main(new String[]{});
    }
}
