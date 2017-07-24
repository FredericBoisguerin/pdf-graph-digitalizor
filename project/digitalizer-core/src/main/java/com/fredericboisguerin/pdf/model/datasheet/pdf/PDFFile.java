package com.fredericboisguerin.pdf.model.datasheet.pdf;

import com.fredericboisguerin.pdf.parser.BorderPoints;
import com.fredericboisguerin.pdf.parser.PDFDrawingActionsParser;
import com.fredericboisguerin.pdf.parser.ParsedPage;
import com.fredericboisguerin.pdf.parser.model.DrawingAction;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFFile {
    private final String filename;
    private final byte[] file;

    public PDFFile(String filename, byte[] file) {
        this.filename = filename;
        this.file = file;
    }

    @Override
    public String toString() {
        return filename;
    }

    private ParsedPage getParsedPage(int pageIndex) throws IOException {
        PDDocument pdDocument = PDFDrawingActionsParser.loadDocument(file);
        return PDFDrawingActionsParser.parseDocument(pageIndex, pdDocument);
    }

    List<DrawingAction> getDrawingActionsCroppedBy(ImageCrop imageCrop, int pageIndex) throws IOException {
        ParsedPage parsedPage = getParsedPage(pageIndex);
        BorderPoints borderPoints = imageCrop.applyTo(parsedPage.getBorderPoints());
        return parsedPage.getDrawingActionsIn(borderPoints);
    }

    PDFImage asPdfImage(String formatName, int pageIndex) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage bufferedImage = getParsedPage(pageIndex).getBufferedImage();
        ImageIO.write(bufferedImage, formatName, baos);
        return new PDFImage(baos.toByteArray(), bufferedImage.getWidth(), bufferedImage.getHeight());
    }

    public PDFPage getPage(int pageIndex) {
        return new PDFPage(this, pageIndex);
    }

    public int getNbPages() {
        try {
            PDDocument pdDocument = PDDocument.load(file);
            return pdDocument.getNumberOfPages();
        } catch (IOException e) {
            return 0;
        }
    }
}
