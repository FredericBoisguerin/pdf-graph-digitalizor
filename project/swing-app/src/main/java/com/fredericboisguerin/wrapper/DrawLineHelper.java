package com.fredericboisguerin.wrapper;

import java.util.function.Function;

import com.fredericboisguerin.pdf.drawlines.model.DrawLine;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

public class DrawLineHelper {

    private final CoordComparator coordComparator = new CoordComparator();

    public boolean isVertical(DrawLine drawLine) {
        return hasAtLeastTwoPoints(drawLine) && isCoordTheSameForAll(drawLine, DrawingPoint::getX);
    }

    public boolean isHorizontal(DrawLine drawLine) {
        return hasAtLeastTwoPoints(drawLine) && isCoordTheSameForAll(drawLine, DrawingPoint::getY);
    }

    public boolean isSerie(DrawLine drawLine) {
        return drawLine.hasAtLeast(3) && !isVertical(drawLine) && !isHorizontal(drawLine);
    }

    private boolean isCoordTheSameForAll(DrawLine drawLine,
            Function<DrawingPoint, Float> coordGetter) {
        DrawingPoint firstPoint = drawLine.getFirstPoint();
        float coord = coordGetter.apply(firstPoint);
        return areAllCoordsEqualTo(drawLine, coord, coordGetter);
    }

    private boolean areCoordEqual(float c1, float c2) {

        return coordComparator.compare(c1, c2) == 0;
    }

    private boolean areAllCoordsEqualTo(DrawLine drawLine, float coord,
            Function<DrawingPoint, Float> coordGetter) {
        return drawLine.allPointsMatch(
                drawingPoint -> areCoordEqual(coord, coordGetter.apply(drawingPoint)));
    }

    private static boolean hasAtLeastTwoPoints(DrawLine drawLine) {
        return drawLine.hasAtLeast(2);
    }
}
