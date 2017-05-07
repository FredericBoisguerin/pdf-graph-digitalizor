package com.fredericboisguerin.pdf.bezier.discretizer;

import com.fredericboisguerin.pdf.bezier.model.BezierCurve;
import com.fredericboisguerin.pdf.bezier.model.BezierCurvePoint;

import java.util.List;

@FunctionalInterface
public interface BezierCurveDiscretizer {

    List<BezierCurvePoint> getDrawingPointsForBezierCurve(BezierCurve bezierCurve);
}
