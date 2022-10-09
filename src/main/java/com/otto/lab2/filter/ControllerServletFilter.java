package com.otto.lab2.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

@WebFilter("/*")
public class ControllerServletFilter implements Filter {

    private TypeSafeServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterConfig.getServletContext().setAttribute("headers", new ConcurrentHashMap<>());
        context = new TypeSafeServletContext(filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;


        Enumeration<String> reqHeaderNames = request.getHeaderNames();
        while (reqHeaderNames.hasMoreElements()) {
            String currentHeader = reqHeaderNames.nextElement();
            context.incrementHeaderMetCount("headers", currentHeader);
        }

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
