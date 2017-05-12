package com.fredericboisguerin.pdf.actions.extract;

import com.fredericboisguerin.pdf.drawlines.converter.DrawingActionsToDrawLinesConverter;
import com.fredericboisguerin.pdf.drawlines.model.DrawLines;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.infrastructure.wrapper.CoordComparator;
import com.fredericboisguerin.pdf.infrastructure.wrapper.DrawingLinesToXYGraphConverter;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;
import com.fredericboisguerin.pdf.parser.PDFDrawingActionsParser;
import com.fredericboisguerin.pdf.parser.model.DrawingAction;
import java.io.IOException;
import java.util.List;

public class ExtractGraphFromPDFFile {

    private final PDFFile pdfFile;

    public ExtractGraphFromPDFFile(PDFFile pdfFile) {
        this.pdfFile = pdfFile;
    }

    public XYGraph execute() throws IOException {
        PDFDrawingActionsParser pdfDrawingActionsParser = new PDFDrawingActionsParser();
        List<DrawingAction> drawingActions = pdfDrawingActionsParser.parseDrawingActions(pdfFile
                .getBytes());
        DrawingActionsToDrawLinesConverter linesConverter = new DrawingActionsToDrawLinesConverter();
        DrawLines drawLines = linesConverter.convert(drawingActions);
        DrawingLinesToXYGraphConverter graphConverter = new DrawingLinesToXYGraphConverter(new CoordComparator());
        return graphConverter.convert(drawLines);
    }
}
