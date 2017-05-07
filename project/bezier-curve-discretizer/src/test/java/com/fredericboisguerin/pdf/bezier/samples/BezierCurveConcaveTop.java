package com.fredericboisguerin.pdf.bezier.samples;

import com.fredericboisguerin.pdf.bezier.model.BezierCurve;
import com.fredericboisguerin.pdf.bezier.model.BezierCurvePoint;

public class BezierCurveConcaveTop extends BezierCurve {

    private static final BezierCurvePoint p0 = new BezierCurvePoint(0, 2);
    private static final BezierCurvePoint p1 = new BezierCurvePoint(1, 1);
    private static final BezierCurvePoint p2 = new BezierCurvePoint(2, 1);
    private static final BezierCurvePoint p3 = new BezierCurvePoint(3, 2);

    public BezierCurveConcaveTop() {
        super(p0, p1, p2, p3);
    }
}
