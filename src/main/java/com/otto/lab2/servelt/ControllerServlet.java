package com.otto.lab2.servelt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.otto.lab2.entity.TableRow;
import com.otto.lab2.session.TypeSafeSessionWorker;
import com.otto.lab2.util.InvalidTablePostRequestException;
import com.otto.lab2.util.TablePostRequestValidator;
import com.otto.lab2.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", urlPatterns = "/table")
public class ControllerServlet extends HttpServlet {

    private static final String TABLE_ATTRIBUTE_NAME = "table";
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";

    private Validator tablePostRequestValidator;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        tablePostRequestValidator = new TablePostRequestValidator();
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            tablePostRequestValidator.validate(req);
        } catch (InvalidTablePostRequestException e) {
            resp.getWriter().println(e.getMessage());
            resp.setStatus(e.getHttpStatus());
            resp.getWriter().close();
        }

        getServletContext().getRequestDispatcher("/check").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TypeSafeSessionWorker<TableRow> session = new TypeSafeHttpSession(req.getSession());

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType(CONTENT_TYPE);
        resp.setCharacterEncoding(ENCODING);
        resp.getWriter().println(
                mapper.writeValueAsString(
                        session.getAttributeOrDefault(TABLE_ATTRIBUTE_NAME)
                )
        );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TypeSafeSessionWorker<TableRow> session = new TypeSafeHttpSession(req.getSession());
        session.cleanAttribute(TABLE_ATTRIBUTE_NAME);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
