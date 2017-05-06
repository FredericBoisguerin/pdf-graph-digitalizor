package com.fredericboisguerin.pdf.wrapper.bezier.samples;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;
import com.fredericboisguerin.pdf.wrapper.bezier.BezierCurve;

public class BezierCurveSimpleConcaveBottom extends BezierCurve {

    static final DrawingPoint p0 = new DrawingPoint(0, 0);
    static final DrawingPoint p1 = new DrawingPoint(1, 1);
    static final DrawingPoint p2 = new DrawingPoint(2, 1);
    static final DrawingPoint p3 = new DrawingPoint(3, 0);

    public BezierCurveSimpleConcaveBottom() {
        this(p0, p1, p2, p3);
    }

    BezierCurveSimpleConcaveBottom(DrawingPoint p0, DrawingPoint p1, DrawingPoint p2, DrawingPoint p3) {
        super(p0, p1, p2, p3);
    }
}
