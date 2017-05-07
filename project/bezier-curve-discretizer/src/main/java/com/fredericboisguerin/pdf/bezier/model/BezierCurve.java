package com.fredericboisguerin.pdf.bezier.model;

import java.util.function.Function;

public class BezierCurve {
    private final BezierCurvePoint p0;
    private final BezierCurvePoint p1;
    private final BezierCurvePoint p2;
    private final BezierCurvePoint p3;

    public BezierCurve(BezierCurvePoint p0, BezierCurvePoint p1, BezierCurvePoint p2, BezierCurvePoint p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public BezierCurvePoint getP0() {
        return p0;
    }

    public BezierCurvePoint getP1() {
        return p1;
    }

    public BezierCurvePoint getP2() {
        return p2;
    }

    public BezierCurvePoint getP3() {
        return p3;
    }

    public BezierCurvePoint getDrawingPointForT(float t) {
        double c0 = Math.pow(1 - t, 3);
        double c1 = 3 * t * Math.pow(1 - t, 2);
        double c2 = 3 * Math.pow(t, 2) * (1 - t);
        double c3 = Math.pow(t, 3);

        float x = getCoord(BezierCurvePoint::getX, c0, c1, c2, c3);
        float y = getCoord(BezierCurvePoint::getY, c0, c1, c2, c3);
        return new BezierCurvePoint(x, y);
    }

    private float getCoord(Function<BezierCurvePoint, Float> coordGetter, double k0, double k1, double k2, double k3) {
        double c0 = getProduct(coordGetter, k0, p0);
        double c1 = getProduct(coordGetter, k1, p1);
        double c2 = getProduct(coordGetter, k2, p2);
        double c3 = getProduct(coordGetter, k3, p3);

        double sum = c0 + c1 + c2 + c3;
        return doubleToFloat(sum);

    }

    private static float doubleToFloat(double sum) {
        return Double.valueOf(sum)
                     .floatValue();
    }

    private double getProduct(Function<BezierCurvePoint, Float> coordGetter, double k, BezierCurvePoint point) {
        return k * coordGetter.apply(point);
    }
}
