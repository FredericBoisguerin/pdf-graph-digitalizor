package com.fredericboisguerin.pdf.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XYGraph implements Iterable<XYPointSeries> {

    private final List<XYPointSeries> xyPointSeries = new ArrayList<>();
    private final Axis xAxis;
    private final Axis yAxis;

    public XYGraph(Axis xAxis, Axis yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public void remove(XYPointSeries xyPointSeries) {
        this.xyPointSeries.remove(xyPointSeries);
        Collections.sort(this.xyPointSeries);
    }

    public XYGraph changeAxes(Axis xAxis, Axis yAxis, CoordConverterProvider coordConverterProvider) {
        XYGraph xyGraph = new XYGraph(xAxis, yAxis);
        Function<Coord, Coord> xConverter = coordConverterProvider.getConverter(this.xAxis, xAxis);
        Function<Coord, Coord> yConverter = coordConverterProvider.getConverter(this.yAxis, yAxis);
        List<XYPointSeries> collect = xyPointSeries.stream()
                                                   .map(series -> series.convert(xConverter, yConverter))
                                                   .collect(Collectors.toList());
        xyGraph.xyPointSeries.addAll(collect);
        return xyGraph;
    }

    @Override
    public String toString() {
        return String.format("%d curves\nX axis: %s\nY axis: %s", xyPointSeries.size(), xAxis, yAxis);
    }

    @Override
    public Iterator<XYPointSeries> iterator() {
        return xyPointSeries.iterator();
    }
}
