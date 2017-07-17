package com.fredericboisguerin.pdf.model.datasheet;

import com.fredericboisguerin.pdf.parser.PDFDrawingActionsParser;
import com.fredericboisguerin.pdf.parser.model.DrawingAction;

import java.io.IOException;
import java.util.List;

public class PDFFile {
    private final String filename;
    private final byte[] file;

    public PDFFile(String filename, byte[] file) {
        this.filename = filename;
        this.file = file;
    }

    public List<DrawingAction> getDrawingActions() throws IOException {
        PDFDrawingActionsParser pdfDrawingActionsParser = new PDFDrawingActionsParser();
        return pdfDrawingActionsParser.parseDrawingActions(file);
    }

    @Override
    public String toString() {
        return filename;
    }
}
