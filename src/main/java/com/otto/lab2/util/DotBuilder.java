package com.otto.lab2.util;

import com.otto.lab2.entity.Dot;

import javax.servlet.http.HttpServletRequest;

public class DotBuilder implements Builder<Dot> {

    private static final String X_PARAM = "x";
    private static final String Y_PARAM = "y";
    private static final String R_PARAM = "r";

    @Override
    public Dot build(HttpServletRequest request) {
        return new Dot(
                Double.parseDouble(request.getParameter(X_PARAM)),
                Double.parseDouble(request.getParameter(Y_PARAM)),
                Integer.parseInt(request.getParameter(R_PARAM))
        );
    }
}
