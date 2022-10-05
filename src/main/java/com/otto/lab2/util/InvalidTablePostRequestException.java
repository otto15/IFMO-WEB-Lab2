package com.otto.lab2.util;

public class InvalidTablePostRequestException extends RuntimeException {

    private static final int HTTP_STATUS = 400;

    public InvalidTablePostRequestException(String message) {
        super(message);
    }

    public int getHttpStatus() {
        return HTTP_STATUS;
    }
}
