package com.fredericboisguerin.pdf.parser;

import com.fredericboisguerin.pdf.parser.model.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.PageDrawer;
import org.apache.pdfbox.rendering.PageDrawerParameters;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PDFDrawingActionsParser {

    private PDFDrawingActionsParser() {
        // This is static
    }

    private static ParsedPage parsePDFDocumentPage(PDDocument doc, int pageIndex) throws IOException {
        BorderPoints borderPoints = getBorderPoints(doc, pageIndex);
        DrawingActionsWithImage drawingActionsWithImage = renderPDFDocumentPage(doc, pageIndex);
        return new ParsedPage(drawingActionsWithImage, borderPoints);
    }

    private static DrawingActionsWithImage renderPDFDocumentPage(PDDocument doc, int pageIndex) throws IOException {
        MyPDFRenderer renderer = new MyPDFRenderer(doc);
        BufferedImage bufferedImage = renderer.renderImage(pageIndex);
        doc.close();
        return new DrawingActionsWithImage(renderer.actionList, bufferedImage);
    }

    private static BorderPoints getBorderPoints(PDDocument doc, int pageIndex) {
        PDPage page = doc.getPage(pageIndex);
        PDRectangle cropBox = page.getCropBox();
        DrawingPoint lowerLeft = new DrawingPoint(cropBox.getLowerLeftX(), cropBox.getLowerLeftY());
        DrawingPoint upperRight = new DrawingPoint(cropBox.getUpperRightX(), cropBox.getUpperRightY());
        return new BorderPoints(lowerLeft, upperRight);
    }

    public static ParsedPage parseDocument(byte[] file, int pageIndex) throws IOException {
        return parsePDFDocumentPage(PDDocument.load(file), pageIndex);
    }

    private static class MyPDFRenderer extends PDFRenderer {
        List<DrawingAction> actionList;

        MyPDFRenderer(PDDocument document) {
            super(document);
        }

        @Override
        protected PageDrawer createPageDrawer(PageDrawerParameters parameters) throws IOException {
            this.actionList = new ArrayList<>();
            return new MyPageDrawer(parameters, actionList);
        }
    }

    private static class MyPageDrawer extends PageDrawer {

        private final List<DrawingAction> actionList;

        MyPageDrawer(PageDrawerParameters parameters, List<DrawingAction> actionList) throws IOException {
            super(parameters);
            this.actionList = actionList;
        }

        @Override
        public void moveTo(float x, float y) {
            super.moveTo(x, y);

            DrawingPoint destination = buildDrawingPoint(x, y);
            MoveTo moveTo = new MoveTo(destination);
            actionList.add(moveTo);
        }

        @Override
        public void lineTo(float x, float y) {
            super.lineTo(x, y);

            DrawingPoint destination = buildDrawingPoint(x, y);
            LineTo lineTo = new LineTo(destination);
            actionList.add(lineTo);
        }

        @Override
        public void curveTo(float x1, float y1, float x2, float y2, float x3, float y3) {
            super.curveTo(x1, y1, x2, y2, x3, y3);

            CurveTo curveTo = new CurveTo(buildDrawingPoint(x1, y1), buildDrawingPoint(x2, y2), buildDrawingPoint(x3, y3));
            actionList.add(curveTo);
        }

        private DrawingPoint buildDrawingPoint(float x, float y) {
            return new DrawingPoint(x, y);
        }
    }
}
