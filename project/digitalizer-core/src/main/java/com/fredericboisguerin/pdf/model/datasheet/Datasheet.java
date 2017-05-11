package com.fredericboisguerin.pdf.model.datasheet;

import java.util.UUID;

public class Datasheet {
    private final UUID uuid = UUID.randomUUID();
    private final DatasheetReference datasheetReference;
    private final DatasheetSupplier supplier;
    private final PDFFile pdfFile;

    public Datasheet(DatasheetReference datasheetReference, DatasheetSupplier supplier,
                     PDFFile pdfFile) {
        this.datasheetReference = datasheetReference;
        this.supplier = supplier;
        this.pdfFile = pdfFile;
    }

    public DatasheetReference getDatasheetReference() {
        return datasheetReference;
    }

    public DatasheetSupplier getSupplier() {
        return supplier;
    }

    public boolean idEquals(String parameter) {
        UUID other = UUID.fromString(parameter);
        return this.uuid.equals(other);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", datasheetReference, supplier);
    }

    public PDFFile getPDFFile() {
        return pdfFile;
    }

    public String getId() {
        return uuid.toString();
    }
}
