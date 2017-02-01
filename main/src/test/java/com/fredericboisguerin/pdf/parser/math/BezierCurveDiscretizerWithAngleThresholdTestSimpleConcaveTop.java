package com.fredericboisguerin.pdf.parser.math;

import com.fredericboisguerin.pdf.parser.model.BezierCurve;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

public class BezierCurveDiscretizerWithAngleThresholdTestSimpleConcaveTop
        extends AbstractBezierCurveDiscretizerWithAngleThresholdTest {

    private final DrawingPoint p0 = new DrawingPoint(0, 2);
    private final DrawingPoint p1 = new DrawingPoint(1, 1);
    private final DrawingPoint p2 = new DrawingPoint(2, 1);
    private final DrawingPoint p3 = new DrawingPoint(3, 2);

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
