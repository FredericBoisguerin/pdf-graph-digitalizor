package com.fredericboisguerin.pdf.actions.export;

import com.fredericboisguerin.pdf.graph.Axis;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.infrastructure.report.ReportGenerator;
import com.fredericboisguerin.pdf.infrastructure.report.ReportGeneratorImpl;
import com.fredericboisguerin.pdf.model.DatasheetGraphExtraInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

import java.io.File;
import java.util.Collection;
import java.util.UUID;

public class ExportDataExcel {
    private final ExportGraphData exportGraphData;
    private final String datasheetId;
    private final String graphId;

    public ExportDataExcel(String datasheetId, String graphId, ExportGraphData exportGraphData) {
        this.exportGraphData = exportGraphData;
        this.datasheetId = datasheetId;
        this.graphId = graphId;
    }

    public File execute(DatasheetService datasheetService) throws Exception {
        XYGraph resizedGraph = getXYGraphForExport();
        DatasheetGraphExtraInfo datasheetGraphExtraInfo = datasheetService.getDatasheetGraphExtraInfo(datasheetId, graphId);
        ReportGenerator reportGenerator = new ReportGeneratorImpl();
        return reportGenerator.generateReport(resizedGraph, datasheetGraphExtraInfo);
    }

    private XYGraph getXYGraphForExport() {
        XYGraph filteredSeries = getGraphWithSelectedSeriesOnly();
        return getTransposedGraph(filteredSeries);
    }

    private XYGraph getGraphWithSelectedSeriesOnly() {
        XYGraph graph = exportGraphData.getGraph();
        Collection<UUID> selectedElements = exportGraphData.getSelectedElements();
        graph.select(selectedElements);
        return graph.withOnlySelected();
    }

    private XYGraph getTransposedGraph(XYGraph filteredSeries) {
        Axis xAxis = exportGraphData.getxAxis();
        Axis yAxis = exportGraphData.getyAxis();
        return filteredSeries.transposeAxesTo(xAxis, yAxis);
    }
}
