package com.fredericboisguerin.pdf.actions.extract;

import com.fredericboisguerin.pdf.drawlines.converter.DrawingActionsToDrawLinesConverter;
import com.fredericboisguerin.pdf.drawlines.model.DrawLines;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.infrastructure.wrapper.CoordComparator;
import com.fredericboisguerin.pdf.infrastructure.wrapper.DrawingLinesToXYGraphConverter;
import com.fredericboisguerin.pdf.model.datasheet.pdf.DatasheetGraphPDF;
import com.fredericboisguerin.pdf.parser.model.DrawingAction;
import java.io.IOException;
import java.util.List;

public class ExtractGraphFromPDFFile {

    private final DatasheetGraphPDF datasheetPdf;

    public ExtractGraphFromPDFFile(DatasheetGraphPDF datasheetPdf) {
        this.datasheetPdf = datasheetPdf;
    }

    public XYGraph execute() throws IOException {
        List<DrawingAction> drawingActions = datasheetPdf.getDrawingActions();
        DrawingActionsToDrawLinesConverter linesConverter = new DrawingActionsToDrawLinesConverter();
        DrawLines drawLines = linesConverter.convert(drawingActions);
        DrawingLinesToXYGraphConverter graphConverter = new DrawingLinesToXYGraphConverter(new CoordComparator());
        return graphConverter.convert(drawLines);
    }

}
