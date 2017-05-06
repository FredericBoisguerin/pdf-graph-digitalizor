package com.fredericboisguerin.utils;

import org.hamcrest.Matcher;

import java.util.List;

public class MyCustomMatchers {

    public static <T> Matcher<List<T>> contains(T element) {
        return new ListContainsElementMatcher<>(element);
    }
}
