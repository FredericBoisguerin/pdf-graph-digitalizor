package com.fredericboisguerin.pdf.model.datasheet;

public class DatasheetMetaInfo implements Comparable<DatasheetMetaInfo> {
    private final DatasheetReference datasheetReference;
    private final DatasheetSupplier supplier;

    public DatasheetMetaInfo(DatasheetReference datasheetReference, DatasheetSupplier supplier) {
        this.datasheetReference = datasheetReference;
        this.supplier = supplier;
    }

    public DatasheetReference getReference() {
        return datasheetReference;
    }

    public DatasheetSupplier getSupplier() {
        return supplier;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", datasheetReference, supplier);
    }

    @Override
    public int compareTo(DatasheetMetaInfo o) {
        return datasheetReference.compareTo(o.datasheetReference);
    }
}
