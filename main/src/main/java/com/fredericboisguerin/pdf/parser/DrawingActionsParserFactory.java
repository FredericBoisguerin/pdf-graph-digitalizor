package com.fredericboisguerin.pdf.parser;

/**
 * Created by fred on 10/01/17.
 */
public class DrawingActionsParserFactory {

    public DrawingActionsParser build() {
        return new PDFDrawingActionsParser();
    }

}
