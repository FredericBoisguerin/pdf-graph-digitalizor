package com.fredericboisguerin.pdf.model;

public class AxisName {
    private final String name;

    public AxisName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
