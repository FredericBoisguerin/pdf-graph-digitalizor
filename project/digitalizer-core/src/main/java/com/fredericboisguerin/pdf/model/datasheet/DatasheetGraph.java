package com.fredericboisguerin.pdf.model.datasheet;

import java.util.UUID;

public class DatasheetGraph {
    private final UUID uuid = UUID.randomUUID();
    private final PDFFile pdfFile;

    public DatasheetGraph(PDFFile pdfFile) {
        this.pdfFile = pdfFile;
    }

    public String getId() {
        return uuid.toString();
    }

    PDFFile getPDFFile() {
        return pdfFile;
    }

    public String getFilename() {
        return pdfFile.toString();
    }
}
