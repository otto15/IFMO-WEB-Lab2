package com.otto.lab2.util;

import javax.servlet.http.HttpServletRequest;

public class TablePostRequestValidator implements Validator {

    private static final String X_PARAM = "x";
    private static final String Y_PARAM = "y";
    private static final String R_PARAM = "r";
    private static final String UTC_OFFSET_PARAM = "offset";
    private static final String SOME_PARAM_NOT_SET_MESSAGE = "Some of the parameters are not set";

    @Override
    public void validate(HttpServletRequest request) throws InvalidTablePostRequestException {
        String x = request.getParameter(X_PARAM);
        String y = request.getParameter(Y_PARAM);
        String r = request.getParameter(R_PARAM);
        String utcOffset = request.getParameter(UTC_OFFSET_PARAM);
        if (x == null || y == null || r == null || utcOffset == null) {
            throw new InvalidTablePostRequestException(SOME_PARAM_NOT_SET_MESSAGE);
        }

        StringBuilder stringBuilder = new StringBuilder();
        try {
            Double.parseDouble(x);
        } catch (NumberFormatException e) {
            stringBuilder.append("Failed to parse x!").append("\n");
        }
        try {
            Double.parseDouble(y);
        } catch (NumberFormatException e) {
            stringBuilder.append("Failed to parse y!").append("\n");
        }
        try {
            Integer.parseInt(r);
        } catch (NumberFormatException e) {
            stringBuilder.append("Failed to parse r!").append("\n");
        }
        try {
            Integer.parseInt(utcOffset);
        } catch (NumberFormatException e) {
            stringBuilder.append("Failed to parse utc offset!").append("\n");
        }
        if (stringBuilder.length() != 0) {
            throw new InvalidTablePostRequestException(stringBuilder.toString());
        }
    }
}
