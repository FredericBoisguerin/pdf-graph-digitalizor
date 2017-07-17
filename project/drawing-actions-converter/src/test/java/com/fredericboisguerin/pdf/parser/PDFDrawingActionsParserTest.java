package com.fredericboisguerin.pdf.parser;

import com.fredericboisguerin.pdf.parser.model.*;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class PDFDrawingActionsParserTest {

    @Test
    public void should_ParseDrawingActions() throws IOException {
        PDFDrawingActionsParser pdfDrawingActionsParser = new PDFDrawingActionsParser();
        InputStream resourceAsStream = getClass().getResourceAsStream("/two_graphs.pdf");

        List<DrawingAction> drawingActions = pdfDrawingActionsParser.parseDrawingActions(resourceAsStream);

        drawingActions.forEach(drawingAction -> drawingAction.accept(new Printer()));
    }

    static class Printer implements DrawingActionVisitor {

        @Override
        public void visit(LineTo lineTo) {
            System.out.println("lineTo " + lineTo.getDestination());
        }

        @Override
        public void visit(MoveTo moveTo) {
            System.out.println();
            System.out.println("moveTo " + moveTo.getDestination());
        }

        @Override
        public void visit(CurveTo curveTo) {
            System.out.println("curveTo " + curveTo.getP1() + "-->" + curveTo.getP3());
        }
    }

}