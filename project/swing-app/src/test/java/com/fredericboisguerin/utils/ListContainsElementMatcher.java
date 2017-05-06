package com.fredericboisguerin.utils;

import org.hamcrest.CustomMatcher;

import java.util.List;

/**
 * Created by fred on 31/01/17.
 */
class ListContainsElementMatcher<T> extends CustomMatcher<List<T>> {

    private T element;

    public ListContainsElementMatcher(T element) {
        super("contains " + element);
        this.element = element;
    }

    @Override
    public boolean matches(Object o) {
        return ((List<T>) o).contains(element);
    }
}
