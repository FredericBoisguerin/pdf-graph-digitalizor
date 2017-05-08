package com.fredericboisguerin.pdf.bezier.discretizer;

import com.fredericboisguerin.pdf.bezier.model.BezierCurve;
import com.fredericboisguerin.pdf.bezier.model.BezierCurvePoint;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractBezierCurveDiscretizerWithAngleThreshold implements BezierCurveDiscretizer {

    private final int angleThreshold;

    protected AbstractBezierCurveDiscretizerWithAngleThreshold(int angleThreshold) {
        this.angleThreshold = angleThreshold;
    }

    @Override
    public List<BezierCurvePoint> getDrawingPointsForBezierCurve(BezierCurve bezierCurve) {
        List<BezierCurvePoint> drawingPoints = new ArrayList<>();
        drawingPoints.add(bezierCurve.getP0());
        float t = 0;
        float nextT;
        while (!drawingPoints.contains(bezierCurve.getP3()) && ((nextT = getNextT(bezierCurve, t)) > t)) {
            BezierCurvePoint drawingPointForT = bezierCurve.getDrawingPointForT(nextT);
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
        BezierCurvePoint startPoint = bezierCurve.getDrawingPointForT(t);
        float middle = t + (nextT - t) / 2;
        BezierCurvePoint middlePoint = bezierCurve.getDrawingPointForT(middle);
        BezierCurvePoint endPoint = bezierCurve.getDrawingPointForT(nextT);
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
    private static double getAngle(BezierCurvePoint drawingPointA, BezierCurvePoint drawingPointC,
                                   BezierCurvePoint drawingPointB) {
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
    private static double squareDistanceBetween(BezierCurvePoint a, BezierCurvePoint b) {
        float deltaX = a.getX() - b.getX();
        float deltaY = a.getY() - b.getY();
        return Math.pow(deltaX, 2) + Math.pow(deltaY, 2);
    }
}
