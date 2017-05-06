package com.fredericboisguerin.pdf.wrapper;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

import java.util.*;
import java.util.function.Function;

/**
 * Created by fred on 10/01/17.
 */
public class DrawLine implements Iterable<DrawingPoint> {

    public static final int MINIMUM_NUMBER_OF_POINTS_TO_BE_SERIES = 2;
    public static final float RATIO_TOLERANCE = 1e-4f;
    private final List<DrawingPoint> drawingPoints = new ArrayList<>();

    public void add(DrawingPoint drawingPoint) {
        drawingPoints.add(drawingPoint);
        Collections.sort(drawingPoints);
    }

    public boolean isVertical() {
        return isCoordTheSameForAll(DrawingPoint::getX);
    }

    public boolean isHorizontal() {
        return isCoordTheSameForAll(DrawingPoint::getY);
    }

    public boolean isSeries() {
        return isValid() && !isVertical() && !isHorizontal();
    }

    public DrawingPoint getMin(Function<DrawingPoint, Float> drawingPointMappingFunction) {
        return drawingPoints.stream()
                            .min(drawingPointComparator(drawingPointMappingFunction))
                            .orElseThrow(IllegalStateException::new);
    }

    public DrawingPoint getMax(Function<DrawingPoint, Float> drawingPointMappingFunction) {
        return drawingPoints.stream()
                            .max(drawingPointComparator(drawingPointMappingFunction))
                            .orElseThrow(IllegalStateException::new);
    }

    private Comparator<DrawingPoint> drawingPointComparator(Function<DrawingPoint, Float> drawingPointMappingFunction) {
        return (o1, o2) -> Float.compare(drawingPointMappingFunction.apply(o1), drawingPointMappingFunction.apply(o2));
    }

    private boolean isValid() {
        return drawingPoints.size() >= MINIMUM_NUMBER_OF_POINTS_TO_BE_SERIES;
    }

    public boolean isCoordTheSameForAll(Function<DrawingPoint, Float> coordGetter) {
        Optional<Float> optionalCoord = getOptionalFirstDrawingPoint().map(coordGetter);
        return optionalCoord.map(coord -> areAllCoordsEqualTo(coord, coordGetter))
                            .orElse(false);
    }

    private boolean areAllCoordsEqualTo(float coord, Function<DrawingPoint, Float> coordGetter) {
        return drawingPoints.stream()
                            .allMatch(point -> {
                                Float other = coordGetter.apply(point);
                                return areCoordEqual(coord, other);
                            });
    }

    private static boolean areCoordEqual(float c1, float c2) {
        float dist = Math.abs(c2 - c1);
        float minAbs = Math.min(c1, c2);
        float ratio = dist / minAbs;
        return Float.compare(ratio, RATIO_TOLERANCE) < 0;
    }

    private Optional<DrawingPoint> getOptionalFirstDrawingPoint() {
        return drawingPoints.stream()
                            .findFirst();
    }

    public DrawingPoint getFirstDrawingPoint() throws NullPointerException {
        return getOptionalFirstDrawingPoint().get();
    }

    @Override
    public Iterator<DrawingPoint> iterator() {
        return drawingPoints.iterator();
    }
}
