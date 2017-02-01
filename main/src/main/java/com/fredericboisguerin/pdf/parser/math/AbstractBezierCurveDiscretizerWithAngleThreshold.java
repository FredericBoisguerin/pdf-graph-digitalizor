package com.fredericboisguerin.pdf.parser.math;

import java.util.ArrayList;
import java.util.List;

import com.fredericboisguerin.pdf.parser.model.BezierCurve;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

/**
 * Created by fred on 31/01/17.
 */
abstract class AbstractBezierCurveDiscretizerWithAngleThreshold implements BezierCurveDiscretizer {

    private final int angleThreshold;

    protected AbstractBezierCurveDiscretizerWithAngleThreshold(int angleThreshold) {
        this.angleThreshold = angleThreshold;
    }

    @Override
    public List<DrawingPoint> getDrawingPointsForBezierCurve(BezierCurve bezierCurve) {
        List<DrawingPoint> drawingPoints = new ArrayList<>();
        drawingPoints.add(bezierCurve.getP0());
        float t = 0;
        while (!drawingPoints.contains(bezierCurve.getP3())) {
            float nextT = getNextT(bezierCurve, t);
            DrawingPoint drawingPointForT = bezierCurve.getDrawingPointForT(nextT);
            drawingPoints.add(drawingPointForT);
            t = nextT;
        }
        return drawingPoints;
    }

    protected abstract float getNextT(float t);

    private float getNextT(BezierCurve bezierCurve, float t) {
        float nextT = getNextT(t);
        while (angleBetween(t, nextT, bezierCurve) < angleThreshold) {
            nextT = t + (nextT - t) / 2;
        }
        return nextT;
    }

    protected static double angleBetween(float t, float nextT, BezierCurve bezierCurve) {
        DrawingPoint startPoint = bezierCurve.getDrawingPointForT(t);
        float middle = t + (nextT - t) / 2;
        DrawingPoint middlePoint = bezierCurve.getDrawingPointForT(middle);
        DrawingPoint endPoint = bezierCurve.getDrawingPointForT(nextT);
        return getAngle(startPoint, middlePoint, endPoint);
    }

    /**
     * Compute the angle between the two vectors CA and CB (in degrees)
     *
     * @param drawingPointA Point A
     * @param drawingPointC Point C
     * @param drawingPointB Point B
     * @return the angle (CA, CB)
     * @see <a href="https://en.wikipedia.org/wiki/Law_of_cosines">Law of cosines</a>
     */
    private static double getAngle(DrawingPoint drawingPointA, DrawingPoint drawingPointC,
            DrawingPoint drawingPointB) {
        double a2 = squareDistanceBetween(drawingPointC, drawingPointB);
        double b2 = squareDistanceBetween(drawingPointA, drawingPointC);
        double c2 = squareDistanceBetween(drawingPointA, drawingPointB);
        double a = Math.sqrt(a2);
        double b = Math.sqrt(b2);
        double angleRad = Math.acos((a2 + b2 - c2) / (2 * a * b));
        return Math.toDegrees(angleRad);
    }

    /**
     * Compute the square distance between the two points A and B
     *
     * @param a Point A
     * @param b Point B
     * @return |AB|²
     */
    private static double squareDistanceBetween(DrawingPoint a, DrawingPoint b) {
        float deltaX = a.getX() - b.getX();
        float deltaY = a.getY() - b.getY();
        return Math.pow(deltaX, 2) + Math.pow(deltaY, 2);
    }
}
