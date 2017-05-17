package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.graph.Axis;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.infrastructure.report.ReportGenerator;
import com.fredericboisguerin.pdf.infrastructure.report.ReportGeneratorImpl;

import java.io.File;
import java.util.Collection;
import java.util.UUID;

public class ExportDataExcel {
    private final XYGraph graph;
    private final Axis xAxis;
    private final Axis yAxis;
    private final Collection<UUID> selectedElements;

    public ExportDataExcel(XYGraph graph, Axis xAxis, Axis yAxis,
                           Collection<UUID> selectedElements) {
        this.graph = graph;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.selectedElements = selectedElements;
    }

    public File execute() throws Exception {
        graph.select(selectedElements);
        XYGraph filteredSeries = graph.withOnlySelected();
        XYGraph resizedGraph = filteredSeries.transposeAxesTo(xAxis, yAxis);
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        return reportGenerator.generateReport(resizedGraph);
    }
}
