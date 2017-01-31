package com.fredericboisguerin.pdf.parser.math;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

import java.util.List;

/**
 * Created by fred on 31/01/17.
 */
@FunctionalInterface
public interface BezierCurveDiscretizer {

    List<DrawingPoint> getDrawingPointsForBezierCurve(DrawingPoint p0, DrawingPoint p1, DrawingPoint p2, DrawingPoint p3);
}
