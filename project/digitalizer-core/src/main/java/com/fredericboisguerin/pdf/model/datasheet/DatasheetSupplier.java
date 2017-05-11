package com.fredericboisguerin.pdf.model.datasheet;

public class DatasheetSupplier {
    private final String name;

    public DatasheetSupplier(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
