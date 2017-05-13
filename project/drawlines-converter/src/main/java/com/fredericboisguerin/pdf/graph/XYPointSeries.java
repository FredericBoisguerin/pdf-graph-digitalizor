package com.fredericboisguerin.pdf.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class XYPointSeries implements Iterable<PointCoords> {

    private final List<PointCoords> pointCoordsCoords = new ArrayList<>();

    public void add(PointCoords pointCoords) {
        this.pointCoordsCoords.add(pointCoords);
    }

    public XYPointSeries convert(Function<Coord, Coord> xTransformation, Function<Coord, Coord> yTransformation) {
        XYPointSeries series = new XYPointSeries();
        pointCoordsCoords.stream()
                         .map(pointCoords -> new PointCoords(xTransformation.apply(pointCoords.getX()), yTransformation.apply(
                           pointCoords.getY())))
                         .forEach(series::add);
        return series;
    }

    @Override
    public String toString() {
        return String.format("Série #%d (%d points)", this.hashCode(), pointCoordsCoords.size());
    }

    @Override
    public Iterator<PointCoords> iterator() {
        return pointCoordsCoords.iterator();
    }

    public int size() {
        return pointCoordsCoords.size();
    }
}
