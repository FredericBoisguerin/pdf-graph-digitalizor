package com.fredericboisguerin.pdf.bezier;

import java.util.List;

@FunctionalInterface
public interface BezierCurveDiscretizer {

    List<BezierCurvePoint> getDrawingPointsForBezierCurve(BezierCurve bezierCurve);
}
