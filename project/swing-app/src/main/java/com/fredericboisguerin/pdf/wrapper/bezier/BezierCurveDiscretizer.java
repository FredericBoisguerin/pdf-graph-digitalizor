package com.fredericboisguerin.pdf.wrapper.bezier;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

import java.util.List;

@FunctionalInterface
public interface BezierCurveDiscretizer {

    List<DrawingPoint> getDrawingPointsForBezierCurve(BezierCurve bezierCurve);
}
