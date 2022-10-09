package com.otto.lab2.session;

import com.otto.lab2.entity.TableRow;
import lombok.Value;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class TypeSafeHttpSessionImpl implements TypeSafeHttpSession<TableRow> {

    HttpSession session;

    @Override
    public List<TableRow> getAttributeOrDefault(String name) {
        Object tableObject = session.getAttribute(name);

        if (tableObject == null) {
            return createNewTableInSession(name);
        }

        List<TableRow> table;

        try {
            table = ((List<?>) tableObject).stream().map(
                    o -> (TableRow) o
            ).collect(Collectors.toList());
        } catch (ClassCastException e) {
            return createNewTableInSession(name);
        }

        return table;
    }

    @Override
    public void updateAttribute(String name, TableRow value) {
        List<TableRow> table = getAttributeOrDefault(name);
        table.add(value);
        session.setAttribute(name, table);
    }

    @Override
    public void cleanAttribute(String name) {
        session.setAttribute(name, new ArrayList<>());
    }

    private List<TableRow> createNewTableInSession(String name) {
        List<TableRow> newTableList = new ArrayList<>();
        session.setAttribute(name, newTableList);
        return newTableList;
    }
}
