package com.fredericboisguerin.pdf.parser;

import com.fredericboisguerin.pdf.parser.model.*;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PDFDrawingActionsParserTest {

    @Test
    public void should_extract_each_of_the_two_graphs() throws IOException, URISyntaxException {
        URL resourceURL = getClass().getResource("/two_graphs.pdf");
        File twoGraphs = new File(resourceURL.toURI());
        byte[] bytes = Files.readAllBytes(twoGraphs.toPath());
        ParsedPage parsedPage = PDFDrawingActionsParser.parseDocument(0, PDFDrawingActionsParser.loadDocument(bytes));
        int lowestX = 0;
        int middleX = 240;
        int uppestX = 500;
        int lowestY = 0;
        int uppestY = 500;

        List<DrawingAction> graphLeft = parsedPage.getDrawingActionsIn(new BorderPoints(new DrawingPoint(lowestX, lowestY), new DrawingPoint(middleX, uppestY)));
        List<DrawingAction> graphRight = parsedPage.getDrawingActionsIn(new BorderPoints(new DrawingPoint(middleX, lowestY), new DrawingPoint(uppestX, uppestY)));

        assertEquals(85, graphLeft.size());
        assertEquals(125, graphRight.size());
    }
}