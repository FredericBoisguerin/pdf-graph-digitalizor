package com.fredericboisguerin.pdf.model.datasheet;

public class PDFFile {
    private final String filename;
    private final byte[] file;

    public PDFFile(String filename, byte[] file) {
        this.filename = filename;
        this.file = file;
    }
}
