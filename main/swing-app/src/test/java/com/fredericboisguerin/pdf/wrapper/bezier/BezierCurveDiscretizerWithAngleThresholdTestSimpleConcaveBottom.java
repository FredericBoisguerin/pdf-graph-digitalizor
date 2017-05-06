package com.fredericboisguerin.pdf.wrapper.bezier;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

public class BezierCurveDiscretizerWithAngleThresholdTestSimpleConcaveBottom
        extends AbstractBezierCurveDiscretizerWithAngleThresholdTest {

    private final DrawingPoint p0 = new DrawingPoint(0, 0);
    private final DrawingPoint p1 = new DrawingPoint(1, 1);
    private final DrawingPoint p2 = new DrawingPoint(2, 1);
    private final DrawingPoint p3 = new DrawingPoint(3, 0);

    @Override
    protected DrawingPoint getP0() {
        return p0;
    }

    @Override
    protected DrawingPoint getP3() {
        return p3;
    }

    @Override
    protected BezierCurve getBezierCurve() {
        return new BezierCurve(p0, p1, p2, p3);
    }
}
