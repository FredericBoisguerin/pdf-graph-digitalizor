package com.fredericboisguerin.pdf.bezier.samples;

import com.fredericboisguerin.pdf.bezier.BezierCurve;
import com.fredericboisguerin.pdf.bezier.BezierCurvePoint;

public class BezierCurveSimpleConcaveBottom extends BezierCurve {

    static final BezierCurvePoint p0 = new BezierCurvePoint(0, 0);
    static final BezierCurvePoint p1 = new BezierCurvePoint(1, 1);
    static final BezierCurvePoint p2 = new BezierCurvePoint(2, 1);
    static final BezierCurvePoint p3 = new BezierCurvePoint(3, 0);

    public BezierCurveSimpleConcaveBottom() {
        this(p0, p1, p2, p3);
    }

    BezierCurveSimpleConcaveBottom(BezierCurvePoint p0, BezierCurvePoint p1, BezierCurvePoint p2, BezierCurvePoint p3) {
        super(p0, p1, p2, p3);
    }
}
