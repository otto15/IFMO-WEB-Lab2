package com.otto.lab2.filter;


import lombok.Value;

import javax.servlet.ServletContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Value
public class TypeSafeServletContext {

    ServletContext context;

    public Map<String, Long> getAllHeaders(String name) {
        Object headersObject = context.getAttribute(name);

        if (headersObject == null) {
            throw new NullPointerException("Damn it, how did you let that happen");
        }

        try {
            Map<?, ?> mapUnderConsideration = (Map<?, ?>) headersObject;
            mapUnderConsideration.forEach((key1, value1) -> {
                String key = (String) key1;
                Long value = (Long) value1;
            });
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Damn it, how did you let that happen");
        }

        return (Map<String, Long>) headersObject;
    }

    public void incrementHeaderMetCount(String attrName, String header) {
        Map<String, Long> headers = getAllHeaders(attrName);
        headers.merge(header, 1L, Long::sum);
    }

}
