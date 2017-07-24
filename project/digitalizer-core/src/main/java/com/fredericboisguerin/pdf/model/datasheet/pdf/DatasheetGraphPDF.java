package com.fredericboisguerin.pdf.model.datasheet.pdf;

import java.io.IOException;
import java.util.List;

import com.fredericboisguerin.pdf.parser.model.DrawingAction;

public class DatasheetGraphPDF {
    public final PDFPage PDFPage;
    private final ImageCrop imageCrop;

    public DatasheetGraphPDF(PDFPage PDFPage, ImageCrop imageCrop) {
        this.PDFPage = PDFPage;
        this.imageCrop = imageCrop;
    }

    public List<DrawingAction> getDrawingActions() throws IOException {
        return PDFPage.getDrawingActionsCroppedBy(this.imageCrop);
    }

    @Override
    public String toString() {
        return PDFPage.toString();
    }
}
