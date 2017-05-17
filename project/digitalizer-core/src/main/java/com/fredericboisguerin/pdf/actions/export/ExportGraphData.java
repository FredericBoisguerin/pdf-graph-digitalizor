package com.fredericboisguerin.pdf.actions.export;

import com.fredericboisguerin.pdf.graph.Axis;
import com.fredericboisguerin.pdf.graph.XYGraph;

import java.util.Collection;
import java.util.UUID;

public class ExportGraphData {
    private final XYGraph graph;
    private final Axis xAxis;
    private final Axis yAxis;
    private final Collection<UUID> selectedElements;

    public ExportGraphData(XYGraph graph, Axis xAxis, Axis yAxis, Collection<UUID> selectedElements) {
        this.graph = graph;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.selectedElements = selectedElements;
    }

    public XYGraph getGraph() {
        return graph;
    }

    public Axis getxAxis() {
        return xAxis;
    }

    public Axis getyAxis() {
        return yAxis;
    }

    public Collection<UUID> getSelectedElements() {
        return selectedElements;
    }
}
