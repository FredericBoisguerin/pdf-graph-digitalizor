package com.fredericboisguerin.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EditableXYGraph extends XYGraph {
    public EditableXYGraph(Axis xAxis, Axis yAxis) {
        super(xAxis, yAxis);
    }

    public void add(XYPointSeries xyPointSeries) {
        this.xyPointSeries.add(xyPointSeries);
        Collections.sort(this.xyPointSeries);
    }

    public void removeAll() {
        xyPointSeries.clear();
    }

    public XYGraph changeAxes(Axis xAxis, Axis yAxis, CoordConverterProvider coordConverterProvider) {
        XYGraph xyGraph = new XYGraph(xAxis, yAxis);
        Function<Coord, Coord> xConverter = coordConverterProvider.getConverter(this.xAxis, xAxis);
        Function<Coord, Coord> yConverter = coordConverterProvider.getConverter(this.yAxis, yAxis);
        List<XYPointSeries> collect = xyPointSeries.stream()
                                                   .map(series -> series.convert(xConverter, yConverter))
                                                   .collect(Collectors.toList());
        collect.forEach(xyGraph.xyPointSeries::add);
        return xyGraph;
    }
}
