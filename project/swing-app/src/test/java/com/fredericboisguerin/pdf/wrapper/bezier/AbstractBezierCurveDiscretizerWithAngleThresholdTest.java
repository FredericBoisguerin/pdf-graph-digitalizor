package com.fredericboisguerin.pdf.wrapper.bezier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;
import com.fredericboisguerin.pdf.wrapper.bezier.BezierCurve;
import com.fredericboisguerin.pdf.wrapper.bezier.BezierCurveDiscretizer;
import com.fredericboisguerin.pdf.wrapper.bezier.BezierCurveDiscretizerWithAngleThresholdV1;
import org.junit.Test;

import com.fredericboisguerin.utils.MyCustomMatchers;

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