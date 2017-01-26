package com.fredericboisguerin.graph.model.input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fred on 10/01/17.
 */
public class DrawLine implements Iterable<DrawingPoint> {

    private final List<DrawingPoint> drawingPoints = new ArrayList<>();

    public void add(DrawingPoint drawingPoint) {
        drawingPoints.add(drawingPoint);
        Collections.sort(drawingPoints);
    }

    public boolean containsAll(DrawingPoint... drawingPoints) {
        return this.drawingPoints.containsAll(Arrays.asList(drawingPoints));
    }

    public int size() {
        return drawingPoints.size();
    }

    @Override
    public Iterator<DrawingPoint> iterator() {
        return drawingPoints.iterator();
    }

    public boolean hasRedondant(DrawingPoint datum) {
        int i = drawingPoints.indexOf(datum);
        if (i == 0 || i == drawingPoints.size() - 1) {
            return false;
        } else {
            DrawingPoint before = drawingPoints.get(i - 1);
            DrawingPoint after = drawingPoints.get(i + 1);
            return datum.isAlignedWith(before, after);
        }
    }
}
