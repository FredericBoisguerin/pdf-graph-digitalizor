package com.fredericboisguerin.pdf.parser.math;

import com.fredericboisguerin.pdf.parser.model.BezierCurve;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;
import com.fredericboisguerin.utils.MyCustomMatchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by fred on 31/01/17.
 */
public class BezierCurveDiscretizerWithAngleThresholdTest {

    private final DrawingPoint p0 = new DrawingPoint(0, 0);
    private final DrawingPoint p1 = new DrawingPoint(1, 1);
    private final DrawingPoint p2 = new DrawingPoint(2, 1);
    private final DrawingPoint p3 = new DrawingPoint(3, 0);
    private final BezierCurve bezierCurve = new BezierCurve(p0, p1, p2, p3);


    private List<DrawingPoint> getDrawingPointsForBezierCurveWithAngleThreshold(int angleThreshold) {
        BezierCurveDiscretizerWithAngleThreshold curveDiscretizer = new BezierCurveDiscretizerWithAngleThreshold(angleThreshold);
        return curveDiscretizer.getDrawingPointsForBezierCurve(bezierCurve);
    }

    @Test
    public void should_contains_P0() {
        List<DrawingPoint> drawingPoints = getDrawingPointsForBezierCurveWithAngleThreshold(90);

        assertThat(drawingPoints, MyCustomMatchers.contains(p0));
    }

    @Test
    public void should_contains_P3() {
        List<DrawingPoint> drawingPoints = getDrawingPointsForBezierCurveWithAngleThreshold(90);

        assertThat(drawingPoints, MyCustomMatchers.contains(p3));
    }


    @Test
    public void should_contains_13_points_when_angle_threshold_is_175() {
        List<DrawingPoint> drawingPoints = getDrawingPointsForBezierCurveWithAngleThreshold(175);

        assertThat(drawingPoints.size(), is(13));
    }

    @Test
    public void should_contains_36_points_when_angle_threshold_is_178() {
        List<DrawingPoint> drawingPoints = getDrawingPointsForBezierCurveWithAngleThreshold(178);

        assertThat(drawingPoints.size(), is(36));
    }

    @Test
    public void should_contains_69_points_when_angle_threshold_is_179() {
        List<DrawingPoint> drawingPoints = getDrawingPointsForBezierCurveWithAngleThreshold(179);

        assertThat(drawingPoints.size(), is(69));
    }
}