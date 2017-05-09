package com.fredericboisguerin.pdf.parser;

import com.fredericboisguerin.pdf.parser.model.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.PageDrawer;
import org.apache.pdfbox.rendering.PageDrawerParameters;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFDrawingActionsParser {

    public List<DrawingAction> parseDrawingActions(File file) throws IOException {
        PDDocument doc = PDDocument.load(file);
        MyPDFRenderer renderer = new MyPDFRenderer(doc);
        renderer.renderImage(0);
        doc.close();
        return renderer.actionList;
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
        public void appendRectangle(Point2D p0, Point2D p1, Point2D p2, Point2D p3) {
            super.appendRectangle(p0, p1, p2, p3);

            System.out.println("Rectangle:");
            System.out.println(p0);
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);

            actionList.add(new MoveTo(buildDrawingPoint(p0)));
            actionList.add(new LineTo(buildDrawingPoint(p1)));

            actionList.add(new MoveTo(buildDrawingPoint(p1)));
            actionList.add(new LineTo(buildDrawingPoint(p2)));

            actionList.add(new MoveTo(buildDrawingPoint(p2)));
            actionList.add(new LineTo(buildDrawingPoint(p3)));

            actionList.add(new MoveTo(buildDrawingPoint(p3)));
            actionList.add(new LineTo(buildDrawingPoint(p0)));
        }

        private DrawingPoint buildDrawingPoint(Point2D point2D) {
            return new DrawingPoint((float) point2D.getX(), (float) point2D.getY());
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
