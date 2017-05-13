package com.fredericboisguerin.pdf.actions;

import java.io.File;
import java.util.List;

import com.fredericboisguerin.pdf.graph.Axis;
import com.fredericboisguerin.pdf.graph.CoordConverterProviderImpl;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.graph.Serie;
import com.fredericboisguerin.pdf.infrastructure.report.ReportGenerator;
import com.fredericboisguerin.pdf.infrastructure.report.ReportGeneratorImpl;

public class ExportDataExcel {
    private final XYGraph graph;
    private final Axis xAxis;
    private final Axis yAxis;
    private final List<Serie> selectedElements;

    public ExportDataExcel(XYGraph graph, Axis xAxis, Axis yAxis,
            List<Serie> selectedElements) {
        this.graph = graph;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.selectedElements = selectedElements;
    }

    public File execute() throws Exception {
        XYGraph filteredSeries = graph.withOnly(selectedElements);
        CoordConverterProviderImpl coordConverterProvider = new CoordConverterProviderImpl();
        XYGraph resizedGraph = filteredSeries.changeAxes(xAxis, yAxis, coordConverterProvider);
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        return reportGenerator.generateReport(resizedGraph);
    }
}
