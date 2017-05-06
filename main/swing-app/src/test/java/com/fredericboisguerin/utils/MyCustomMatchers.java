package com.fredericboisguerin.utils;

import org.hamcrest.Matcher;

import java.util.List;

/**
 * Created by fred on 31/01/17.
 */
public class MyCustomMatchers {

    public static <T> Matcher<List<T>> contains(T element) {
        return new ListContainsElementMatcher<>(element);
    }
}
