package com.uk.companieshouse.expectionalhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity handleHttpClientErrorException(HttpClientErrorException httpClientErrorException) {
        if (httpClientErrorException.getRawStatusCode() == 404) {
            return new ResponseEntity(httpClientErrorException.getStatusText(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
