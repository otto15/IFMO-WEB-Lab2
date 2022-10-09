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


        Map<?, ?> mapUnderConsideration = (Map<?, ?>) headersObject;
        Map<String, Long> headers = mapUnderConsideration.entrySet()
                .stream()
                .collect(Collectors.toMap(e -> (String) e.getKey(), e -> (Long) e.getValue()));


        return (Map<String, Long>) headersObject;
    }

    public void incrementHeaderMetCount(String attrName, String header) {
        Map<String, Long> headers = getAllHeaders(attrName);
        headers.merge(header, 1L, Long::sum);
    }

}
