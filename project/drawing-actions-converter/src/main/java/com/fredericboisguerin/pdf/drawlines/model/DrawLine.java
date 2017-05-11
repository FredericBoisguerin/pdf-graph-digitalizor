package com.fredericboisguerin.pdf.drawlines.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DrawLine {

    private final List<DrawPoint> DrawPoints = new ArrayList<>();

    public DrawLine(DrawPoint startingPoint) {
        add(startingPoint);
    }

    public void add(DrawPoint DrawPoint) {
        DrawPoints.add(DrawPoint);
        Collections.sort(DrawPoints);
    }

    public DrawPoint getFirstPoint() {
        return DrawPoints.get(0);
    }

    public boolean allPointsMatch(Predicate<? super DrawPoint> predicate) {
        return DrawPoints.stream().allMatch(predicate);
    }

    public boolean hasAtLeast(int nbOfPoints) {
        return DrawPoints.size() >= nbOfPoints;
    }

    public DrawPoint getMin(Comparator<DrawPoint> comparator) {
        return DrawPoints.stream()
                            .min(comparator)
                            .orElseThrow(IllegalStateException::new);
    }

    public DrawPoint getMax(Comparator<DrawPoint> comparator) {
        return DrawPoints.stream()
                            .max(comparator)
                            .orElseThrow(IllegalStateException::new);
    }

    public Stream<DrawPoint> stream() {
        return DrawPoints.stream();
    }
}
