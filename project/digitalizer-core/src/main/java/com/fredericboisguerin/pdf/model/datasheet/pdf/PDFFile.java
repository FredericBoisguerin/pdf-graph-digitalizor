package com.fredericboisguerin.pdf.model.datasheet.pdf;

import com.fredericboisguerin.pdf.parser.BorderPoints;
import com.fredericboisguerin.pdf.parser.PDFDrawingActionsParser;
import com.fredericboisguerin.pdf.parser.ParsedPDFDocument;
import com.fredericboisguerin.pdf.parser.model.DrawingAction;

import java.io.IOException;
import java.util.List;

public class PDFFile {
    private final String filename;
    private final byte[] file;
    private final ImageCrop imageCrop = ImageCrop.noCrop();

    public PDFFile(String filename, byte[] file) {
        this.filename = filename;
        this.file = file;
    }

    public List<DrawingAction> getDrawingActions() throws IOException {
        ParsedPDFDocument parsedPDFDocument = PDFDrawingActionsParser.parseDocument(file);
        BorderPoints borderPoints = imageCrop.applyTo(parsedPDFDocument.getBorderPoints());
        return parsedPDFDocument.getDrawingActionsIn(borderPoints);
    }

    @Override
    public String toString() {
        return filename;
    }
}
