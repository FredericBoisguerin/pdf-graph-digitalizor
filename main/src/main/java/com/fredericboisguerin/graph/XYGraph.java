package com.fredericboisguerin.graph;

import java.util.*;

/**
 * Created by fred on 10/01/17.
 */
public class XYGraph implements Iterable<XYPointSeries> {

    protected final List<XYPointSeries> xyPointSeries = new ArrayList<>();
    protected final Axis xAxis;
    protected final Axis yAxis;

    public XYGraph(Axis xAxis, Axis yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
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
