package com.otto.lab2.servelt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.otto.lab2.filter.TypeSafeServletContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/headers", urlPatterns = "/headers")
public class HeadersServlet extends HttpServlet {

    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";

    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TypeSafeServletContext context = new TypeSafeServletContext(req.getServletContext());
        Map<String, Long> headers = context.getAllHeaders("headers");

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType(CONTENT_TYPE);
        resp.setCharacterEncoding(ENCODING);
        resp.getWriter().println(
                mapper.writeValueAsString(headers)
        );
        resp.getWriter().close();
    }
}
