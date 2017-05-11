package com.fredericboisguerin.pdf.model.datasheet;

public class Datasheet {
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
}
