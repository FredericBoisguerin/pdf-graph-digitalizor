package com.fredericboisguerin.pdf.parser.math;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fredericboisguerin.pdf.parser.model.BezierCurve;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

public class BezierCurveDiscretizerFixedStepTest {

    public static final int NB_POINTS_IN_T_RANGE = 11;
    private final DrawingPoint p0 = new DrawingPoint(0, 0);
    private final DrawingPoint p1 = new DrawingPoint(1, 1);
    private final DrawingPoint p2 = new DrawingPoint(2, 1);
    private final DrawingPoint p3 = new DrawingPoint(3, 0);

    private final BezierCurve bezierCurve = new BezierCurve(p0, p1, p2, p3);
    private final BezierCurve bezierCurveReverse = new BezierCurve(p3, p2, p1, p0);

    private BezierCurveDiscretizer bezierCurveDiscretizer;

    @Before
    public void setUp() {
        bezierCurveDiscretizer = new BezierCurveDiscretizerFixedStep(NB_POINTS_IN_T_RANGE);
    }

    @Test
    public void normal_order() {
        List<DrawingPoint> drawingPointsForBezierCurve = bezierCurveDiscretizer.getDrawingPointsForBezierCurve(
                bezierCurve);

        assertThat(drawingPointsForBezierCurve.size(), is(NB_POINTS_IN_T_RANGE));
    }

    @Test
    public void reverse_order() {
        List<DrawingPoint> drawingPointsForBezierCurve = bezierCurveDiscretizer.getDrawingPointsForBezierCurve(
                bezierCurveReverse);

        assertThat(drawingPointsForBezierCurve.size(), is(NB_POINTS_IN_T_RANGE));
    }
}