package com.fredericboisguerin.pdf.model.datasheet;

public class DatasheetReference implements Comparable<DatasheetReference> {
    private final String reference;

    public DatasheetReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return reference;
    }

    @Override
    public int compareTo(DatasheetReference other) {
        return this.reference.compareTo(other.reference);
    }
}
