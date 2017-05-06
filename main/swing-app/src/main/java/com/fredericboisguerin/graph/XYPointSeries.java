package com.fredericboisguerin.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by fred on 10/01/17.
 */
public class XYPointSeries implements Iterable<XYPoint>, Comparable<XYPointSeries> {

    private final List<XYPoint> xyPoints = new ArrayList<>();

    public void add(XYPoint xyPoint) {
        xyPoints.add(xyPoint);
    }

    public XYPointSeries convert(Function<Coord, Coord> xTransformation, Function<Coord, Coord> yTransformation) {
        XYPointSeries series = new XYPointSeries();
        xyPoints.stream()
                .map(xyPoint -> new XYPoint(xTransformation.apply(xyPoint.getX()), yTransformation.apply(xyPoint.getY())))
                .forEach(series::add);
        return series;
    }

    @Override
    public String toString() {
        return String.format("Série #%d (%d points)", this.hashCode(), xyPoints.size());
    }

    @Override
    public Iterator<XYPoint> iterator() {
        return xyPoints.iterator();
    }

    @Override
    public int compareTo(XYPointSeries other) {
        return Integer.compare(other.xyPoints.size(), this.xyPoints.size());
    }
}
