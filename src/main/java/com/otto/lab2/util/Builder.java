package com.otto.lab2.util;

import javax.servlet.http.HttpServletRequest;

public interface Builder<T> {
    T build(HttpServletRequest request);
}
