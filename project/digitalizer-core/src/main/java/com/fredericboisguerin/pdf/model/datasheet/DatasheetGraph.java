package com.fredericboisguerin.pdf.model.datasheet;

import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

public class DatasheetGraph {
    private final PDFFile pdfFile;

    public DatasheetGraph(PDFFile pdfFile) {
        this.pdfFile = pdfFile;
    }

    public PDFFile getPDFFile() {
        return pdfFile;
    }
}
