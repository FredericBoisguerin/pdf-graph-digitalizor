package com.fredericboisguerin.pdf.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XYGraph {

    private final List<Serie> xyPointSeries = new ArrayList<>();
    private final Axis xAxis;
    private final Axis yAxis;

    public XYGraph(Axis xAxis, Axis yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public void add(Serie xyPoints) {
        this.xyPointSeries.add(xyPoints);
    }

    public XYGraph changeAxes(Axis xAxis, Axis yAxis,
            CoordConverterProvider coordConverterProvider) {
        XYGraph xyGraph = new XYGraph(xAxis, yAxis);
        Function<Coord, Coord> xConverter = coordConverterProvider.getConverter(this.xAxis, xAxis);
        Function<Coord, Coord> yConverter = coordConverterProvider.getConverter(this.yAxis, yAxis);
        List<Serie> collect = xyPointSeries.stream()
                                           .map(series -> series.convert(xConverter,
                                                           yConverter))
                                           .collect(Collectors.toList());
        xyGraph.xyPointSeries.addAll(collect);
        return xyGraph;
    }

    @Override
    public String toString() {
        return String.format("%d curves\nX axis: %s\nY axis: %s", xyPointSeries.size(), xAxis,
                yAxis);
    }

    public XYGraph withOnly(List<Serie> selectedElements) {
        XYGraph xyPointSeries = new XYGraph(xAxis, yAxis);
        for (Serie selectedElement : selectedElements) {
            if (this.xyPointSeries.contains(selectedElement)) {
                xyPointSeries.add(selectedElement);
            }
        }
        return xyPointSeries;
    }

    public List<Serie> getSeriesBySizeDesc() {
        Comparator<Serie> comparator = Comparator.comparing(Serie::size).reversed();
        ArrayList<Serie> sorted = new ArrayList<>(this.xyPointSeries);
        sorted.sort(comparator);
        return Collections.unmodifiableList(sorted);
    }
}
