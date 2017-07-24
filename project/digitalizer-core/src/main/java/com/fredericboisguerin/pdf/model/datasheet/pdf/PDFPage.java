package com.fredericboisguerin.pdf.model.datasheet.pdf;

import com.fredericboisguerin.pdf.parser.model.DrawingAction;

import java.io.IOException;
import java.util.List;

public class PDFPage {
    private final PDFFile pdfFile;
    private final int pageIndex;

    public PDFPage(PDFFile pdfFile, int pageIndex) {
        this.pdfFile = pdfFile;
        this.pageIndex = pageIndex;
    }

    @Override
    public String toString() {
        return "Page " + pageIndex + " of " + pdfFile;
    }

    List<DrawingAction> getDrawingActionsCroppedBy(ImageCrop imageCrop) throws IOException {
        return pdfFile.getDrawingActionsCroppedBy(imageCrop, this.pageIndex);
    }

    public PDFImage asPDFImage(String formatName) throws IOException {
        return pdfFile.asPdfImage(formatName, pageIndex);
    }

}
