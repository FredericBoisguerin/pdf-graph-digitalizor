package com.fredericboisguerin.pdf.parser;

import com.fredericboisguerin.pdf.parser.model.*;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PDFDrawingActionsParserTest {

    @Test
    public void should_extract_each_of_the_two_graphs() throws IOException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/two_graphs.pdf");
        DrawingActionsWithImage drawingActionsWithImage = PDFDrawingActionsParser.parseDrawingActions(resourceAsStream);
        int lowestX = 0;
        int middleX = 240;
        int uppestX = 500;
        int lowestY = 0;
        int uppestY = 500;

        List<DrawingAction> graphLeft = drawingActionsWithImage.getActionsIn(new BorderPoints(new DrawingPoint(lowestX, lowestY), new DrawingPoint(middleX, uppestY)));
        List<DrawingAction> graphRight = drawingActionsWithImage.getActionsIn(new BorderPoints(new DrawingPoint(middleX, lowestY), new DrawingPoint(uppestX, uppestY)));

        assertEquals(85, graphLeft.size());
        assertEquals(125, graphRight.size());
    }
}