package com.fredericboisguerin.pdf.actions;

import java.io.File;

import com.fredericboisguerin.pdf.graph.Axis;
import com.fredericboisguerin.pdf.graph.CoordConverterProviderImpl;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.infrastructure.report.ReportGenerator;
import com.fredericboisguerin.pdf.infrastructure.report.ReportGeneratorImpl;

public class ExportDataExcel {
    private final XYGraph graph;
    private final Axis xAxis;
    private final Axis yAxis;

    public ExportDataExcel(XYGraph graph, Axis xAxis, Axis yAxis) {
        this.graph = graph;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public File execute() throws Exception {
        XYGraph resizedGraph = graph.changeAxes(xAxis, yAxis, new CoordConverterProviderImpl());
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        return reportGenerator.generateReport(resizedGraph);
    }
}
