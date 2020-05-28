package com.uk.companieshouse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompaniesHouseApplicationTest {

    @Test
    void main_contextLoad() {
        CompaniesHouseApplication.main(new String[]{});
    }
}
