package com.fredericboisguerin.pdf.actions;

import java.io.File;
import java.util.Enumeration;

import com.fredericboisguerin.pdf.graph.Axis;
import com.fredericboisguerin.pdf.graph.CoordConverterProviderImpl;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.graph.XYPointSeries;
import com.fredericboisguerin.pdf.infrastructure.report.ReportGenerator;
import com.fredericboisguerin.pdf.infrastructure.report.ReportGeneratorImpl;

public class ExportDataExcel {
    private final XYGraph graph;
    private final Axis xAxis;
    private final Axis yAxis;
    private final Enumeration<XYPointSeries> notSelectedElements;

    public ExportDataExcel(XYGraph graph, Axis xAxis, Axis yAxis,
            Enumeration<XYPointSeries> notSelectedElements) {
        this.graph = graph;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.notSelectedElements = notSelectedElements;
    }

    public File execute() throws Exception {
        while (notSelectedElements.hasMoreElements()) {
            XYPointSeries xyPointSeries = notSelectedElements.nextElement();
            graph.remove(xyPointSeries);
        }
        XYGraph resizedGraph = graph.changeAxes(xAxis, yAxis, new CoordConverterProviderImpl());
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        return reportGenerator.generateReport(resizedGraph);
    }
}
