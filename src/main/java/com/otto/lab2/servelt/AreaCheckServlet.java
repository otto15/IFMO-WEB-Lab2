package com.otto.lab2.servelt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.otto.lab2.entity.Dot;
import com.otto.lab2.entity.TableRow;
import com.otto.lab2.service.HitChecker;
import com.otto.lab2.session.TypeSafeHttpSession;
import com.otto.lab2.session.TypeSafeHttpSessionImpl;
import com.otto.lab2.util.DotBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {

    private static final String TABLE_ATTRIBUTE_NAME = "table";
    private static final String UTC_OFFSET_PARAM_NAME = "offset";
    private static final String CONTENT_TYPE = "application/json";
    private static final String ENCODING = "UTF-8";

    private DotBuilder dotBuilder;
    private HitChecker hitChecker;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        dotBuilder = new DotBuilder();
        hitChecker = new HitChecker();
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getDispatcherType().name().equals("FORWARD")) {
            resp.sendError(403, "You are not welcome!");
            return;
        }
        super.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.nanoTime();

        TypeSafeHttpSession<TableRow> session = new TypeSafeHttpSessionImpl(req.getSession());


        Dot dot = dotBuilder.build(req);
        boolean hitCheckResult = hitChecker.check(dot);
        int utcOffset = Integer.parseInt(req.getParameter(UTC_OFFSET_PARAM_NAME));
        TableRow newRow = buildTableRow(dot, utcOffset, hitCheckResult, startTime);

        session.updateAttribute(TABLE_ATTRIBUTE_NAME, newRow);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType(CONTENT_TYPE);
        resp.setCharacterEncoding(ENCODING);
        resp.getWriter().println(
                mapper.writeValueAsString(newRow)
        );
        resp.getWriter().close();
    }

    public TableRow buildTableRow(Dot dot, int utcOffset, boolean hitCheckResult, long startTime) {
        return new TableRow(
                dot.getX(),
                dot.getY(),
                dot.getR(),
                Instant.now().minus(utcOffset, ChronoUnit.MINUTES),
                (System.nanoTime() - startTime) / 1000000d,
                hitCheckResult
        );
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        AreaCheckServlet servlet = new AreaCheckServlet();
        System.out.println(servlet.buildTableRow(new Dot(1, 2, 1), -180, true, start));
    }

}
