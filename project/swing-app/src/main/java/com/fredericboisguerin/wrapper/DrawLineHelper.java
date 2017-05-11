package com.fredericboisguerin.wrapper;

import com.fredericboisguerin.pdf.drawlines.model.DrawLine;
import com.fredericboisguerin.pdf.drawlines.model.DrawPoint;

import java.util.Comparator;
import java.util.function.Function;

public class DrawLineHelper {

    private final Comparator<Float> coordComparator;

    public DrawLineHelper(Comparator<Float> coordComparator) {
        this.coordComparator = coordComparator;
    }

    public boolean isVertical(DrawLine drawLine) {
        return hasAtLeastTwoPoints(drawLine) && isCoordTheSameForAll(drawLine, DrawPoint::getX);
    }

    public boolean isHorizontal(DrawLine drawLine) {
        return hasAtLeastTwoPoints(drawLine) && isCoordTheSameForAll(drawLine, DrawPoint::getY);
    }

    public boolean isSerie(DrawLine drawLine) {
        return drawLine.hasAtLeast(3) && !isVertical(drawLine) && !isHorizontal(drawLine);
    }

    private boolean isCoordTheSameForAll(DrawLine drawLine,
                                         Function<DrawPoint, Float> coordGetter) {
        DrawPoint firstPoint = drawLine.getFirstPoint();
        float coord = coordGetter.apply(firstPoint);
        return areAllCoordsEqualTo(drawLine, coord, coordGetter);
    }

    private boolean areCoordEqual(float c1, float c2) {

        return coordComparator.compare(c1, c2) == 0;
    }

    private boolean areAllCoordsEqualTo(DrawLine drawLine, float coord,
                                        Function<DrawPoint, Float> coordGetter) {
        return drawLine.allPointsMatch(
                drawingPoint -> areCoordEqual(coord, coordGetter.apply(drawingPoint)));
    }

    private static boolean hasAtLeastTwoPoints(DrawLine drawLine) {
        return drawLine.hasAtLeast(2);
    }
}
