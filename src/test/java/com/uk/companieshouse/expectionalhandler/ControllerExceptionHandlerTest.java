package com.uk.companieshouse.expectionalhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerExceptionHandlerTest {
    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    void setUp() {
        controllerExceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    void handleHttpClientErrorException_shouldReturn404AndErrorMessage_whenHttpClientErrorExceptionWith404Occurs() {
        HttpClientErrorException httpClientErrorException =
                new HttpClientErrorException(HttpStatus.NOT_FOUND, "CRN Not Found");

        ResponseEntity responseEntity = controllerExceptionHandler.
                handleHttpClientErrorException(httpClientErrorException);

        assertEquals(404, responseEntity.getStatusCodeValue());
        assertEquals("CRN Not Found", responseEntity.getBody());
    }

    @Test
    void handleHttpClientErrorException_shouldReturn500AndInternalServerErrorMessage_whenNoOtherScenarioMatches() {
        HttpClientErrorException httpClientErrorException =
                new HttpClientErrorException(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, "BandWidthLimit Exceeded");

        ResponseEntity responseEntity = controllerExceptionHandler.
                handleHttpClientErrorException(httpClientErrorException);

        assertEquals(500, responseEntity.getStatusCodeValue());
        assertEquals("Internal Server Error", responseEntity.getBody());
    }

    @Test
    void handleHttpClientErrorException_shouldReturn400AndBadRequestError_whenHttpClientErrorExceptionWith400Occurs() {
        HttpClientErrorException httpClientErrorException =
                new HttpClientErrorException(HttpStatus.BAD_REQUEST, "CRN should only contain alphanumeric characters");

        ResponseEntity responseEntity = controllerExceptionHandler.
                handleHttpClientErrorException(httpClientErrorException);

        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("CRN should only contain alphanumeric characters", responseEntity.getBody());
    }
}
