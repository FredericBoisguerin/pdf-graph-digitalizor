package com.fredericboisguerin.pdf.wrapper.bezier;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;
import com.fredericboisguerin.utils.MyCustomMatchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;

public abstract class AbstractBezierCurveDiscretizerWithAngleThresholdTest {

    protected abstract DrawingPoint getP0();

    protected abstract DrawingPoint getP3();

    protected abstract BezierCurve getBezierCurve();

    private List<DrawingPoint> getDrawingPointsForBezierCurveWithAngleThreshold(
            int angleThreshold) {
        BezierCurveDiscretizer curveDiscretizer = new BezierCurveDiscretizerWithAngleThresholdV1(angleThreshold);
        return curveDiscretizer.getDrawingPointsForBezierCurve(getBezierCurve());
    }

    @Test
    public void should_contains_P0() {
        List<DrawingPoint> drawingPoints = getDrawingPointsForBezierCurveWithAngleThreshold(90);

        assertThat(drawingPoints, MyCustomMatchers.contains(getP0()));
    }

    @Test
    public void should_contains_P3() {
        List<DrawingPoint> drawingPoints = getDrawingPointsForBezierCurveWithAngleThreshold(90);

        assertThat(drawingPoints, MyCustomMatchers.contains(getP3()));
    }

    //TODO ADD SOME TESTS
}