package com.otto.lab2.util;

import javax.servlet.http.HttpServletRequest;

public interface Validator {
    void validate(HttpServletRequest request) throws InvalidTablePostRequestException;
}
