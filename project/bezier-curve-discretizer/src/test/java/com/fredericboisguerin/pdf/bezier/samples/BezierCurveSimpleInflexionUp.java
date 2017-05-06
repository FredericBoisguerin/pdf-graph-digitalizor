package com.fredericboisguerin.pdf.bezier.samples;

import com.fredericboisguerin.pdf.bezier.BezierCurve;
import com.fredericboisguerin.pdf.bezier.BezierCurvePoint;

public class BezierCurveSimpleInflexionUp extends BezierCurve {

    private static final BezierCurvePoint p0 = new BezierCurvePoint(0, 0);
    private static final BezierCurvePoint p1 = new BezierCurvePoint(1, 2);
    private static final BezierCurvePoint p2 = new BezierCurvePoint(2, 0);
    private static final BezierCurvePoint p3 = new BezierCurvePoint(3, 2);

    public BezierCurveSimpleInflexionUp() {
        super(p0, p1, p2, p3);
    }
}
