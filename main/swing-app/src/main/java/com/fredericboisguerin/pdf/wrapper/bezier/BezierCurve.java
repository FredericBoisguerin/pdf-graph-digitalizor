package com.fredericboisguerin.pdf.wrapper.bezier;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

import java.util.function.Function;

/**
 * Created by fred on 01/02/17.
 */
public class BezierCurve {
    private final DrawingPoint p0;
    private final DrawingPoint p1;
    private final DrawingPoint p2;
    private final DrawingPoint p3;

    public BezierCurve(DrawingPoint p0, DrawingPoint p1, DrawingPoint p2, DrawingPoint p3) {
        this.p0 = p0;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public DrawingPoint getP0() {
        return p0;
    }

    public DrawingPoint getP1() {
        return p1;
    }

    public DrawingPoint getP2() {
        return p2;
    }

    public DrawingPoint getP3() {
        return p3;
    }

    public DrawingPoint getDrawingPointForT(float t) {
        double c0 = Math.pow(1 - t, 3);
        double c1 = 3 * t * Math.pow(1 - t, 2);
        double c2 = 3 * Math.pow(t, 2) * (1 - t);
        double c3 = Math.pow(t, 3);

        float x = getCoord(DrawingPoint::getX, c0, c1, c2, c3);
        float y = getCoord(DrawingPoint::getY, c0, c1, c2, c3);
        return new DrawingPoint(x, y);
    }

    private float getCoord(Function<DrawingPoint, Float> coordGetter, double k0, double k1, double k2, double k3) {
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

    private double getProduct(Function<DrawingPoint, Float> coordGetter, double k, DrawingPoint drawingPoint) {
        return k * coordGetter.apply(drawingPoint);
    }
}
