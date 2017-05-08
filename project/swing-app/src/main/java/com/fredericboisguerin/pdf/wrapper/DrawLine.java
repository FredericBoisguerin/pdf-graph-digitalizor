package com.fredericboisguerin.pdf.wrapper;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DrawLine {

    private final List<DrawingPoint> drawingPoints = new ArrayList<>();

    public DrawLine(DrawingPoint startingPoint) {
        add(startingPoint);
    }

    public void add(DrawingPoint drawingPoint) {
        drawingPoints.add(drawingPoint);
        Collections.sort(drawingPoints);
    }

    public DrawingPoint getFirstPoint() {
        return drawingPoints.get(0);
    }

    public boolean allPointsMatch(Predicate<? super DrawingPoint> predicate) {
        return drawingPoints.stream().allMatch(predicate);
    }

    public boolean hasAtLeast(int nbOfPoints) {
        return drawingPoints.size() >= nbOfPoints;
    }

    public DrawingPoint getMin(Comparator<DrawingPoint> comparator) {
        return drawingPoints.stream()
                            .min(comparator)
                            .orElseThrow(IllegalStateException::new);
    }

    public DrawingPoint getMax(Comparator<DrawingPoint> comparator) {
        return drawingPoints.stream()
                            .max(comparator)
                            .orElseThrow(IllegalStateException::new);
    }


    public Stream<DrawingPoint> stream() {
        return drawingPoints.stream();
    }
}
