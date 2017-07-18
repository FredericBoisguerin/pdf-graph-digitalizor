package com.fredericboisguerin.pdf.model.datasheet.pdf;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.fredericboisguerin.pdf.parser.BorderPoints;
import com.fredericboisguerin.pdf.parser.PDFDrawingActionsParser;
import com.fredericboisguerin.pdf.parser.ParsedPDFDocument;
import com.fredericboisguerin.pdf.parser.model.DrawingAction;

public class PDFFile {
    private final String filename;
    private final byte[] file;
    private ImageCrop imageCrop = ImageCrop.noCrop();

    public PDFFile(String filename, byte[] file) {
        this.filename = filename;
        this.file = file;
    }

    public List<DrawingAction> getDrawingActions() throws IOException {
        ParsedPDFDocument parsedPDFDocument = getParsedPDFDocument();
        BorderPoints borderPoints = imageCrop.applyTo(parsedPDFDocument.getBorderPoints());
        return parsedPDFDocument.getDrawingActionsIn(borderPoints);
    }

    private ParsedPDFDocument getParsedPDFDocument() throws IOException {
        return PDFDrawingActionsParser.parseDocument(file);
    }

    @Override
    public String toString() {
        return filename;
    }

    public PDFImage asPDFImage(String formatName) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage bufferedImage = getParsedPDFDocument().getBufferedImage();
        ImageIO.write(bufferedImage, formatName, baos);
        return new PDFImage(baos.toByteArray(), bufferedImage.getWidth(), bufferedImage.getHeight());
    }

    public void resetCrop() {
        this.imageCrop = ImageCrop.noCrop();
    }

    public void setCrop(ImageCrop crop) {
        this.imageCrop = crop;
    }
}
