package com.otto.lab2.session;


import java.util.List;

public interface TypeSafeSessionWorker<E> {
    List<E> getAttributeOrDefault(String name);
    void updateAttribute(String name, E value);
    void cleanAttribute(String name);
}
