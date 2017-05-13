package com.fredericboisguerin.pdf.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class XYGraph {

    private final List<Serie> xyPointSeries = new ArrayList<>();
    private final Series series = new Series();

    private final Axis xAxis;
    private final Axis yAxis;

    public XYGraph(Axis xAxis, Axis yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public void add(Serie serie) {
        this.xyPointSeries.add(serie);
        this.series.add(serie);
    }

    public XYGraph transposeAxesTo(Axis xAxis, Axis yAxis) {
        XYGraph xyGraph = new XYGraph(xAxis, yAxis);
        CoordConverterProvider coordConverterProvider = new CoordConverterProvider();
        Function<Coord, Coord> xConverter = coordConverterProvider.getConverter(this.xAxis, xAxis);
        Function<Coord, Coord> yConverter = coordConverterProvider.getConverter(this.yAxis, yAxis);
        series.addTransposedSeriesTo(xyGraph.series, xConverter, yConverter);
        return xyGraph;
    }

    @Override
    public String toString() {
        return String.format("%d curves\nX axis: %s\nY axis: %s", series.size(), xAxis,
                yAxis);
    }

    public XYGraph withOnlySelected() {
        XYGraph xyGraph = new XYGraph(xAxis, yAxis);
        this.series.addOnlySelectedTo(xyGraph.series);
        return xyGraph;
    }

    public List<Serie> getSeriesBySizeDesc() {
        return series.getSeriesBySizeDesc();

    }

    public void select(List<Serie> selectedElements) {
        selectedElements.forEach(series::select);
    }
}
