package com.fredericboisguerin.pdf.wrapper.bezier.samples;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;
import com.fredericboisguerin.pdf.wrapper.bezier.BezierCurve;

public class BezierCurveSimpleInflexionUp extends BezierCurve {

    private static final DrawingPoint p0 = new DrawingPoint(0, 0);
    private static final DrawingPoint p1 = new DrawingPoint(1, 2);
    private static final DrawingPoint p2 = new DrawingPoint(2, 0);
    private static final DrawingPoint p3 = new DrawingPoint(3, 2);

    public BezierCurveSimpleInflexionUp() {
        super(p0, p1, p2, p3);
    }
}
