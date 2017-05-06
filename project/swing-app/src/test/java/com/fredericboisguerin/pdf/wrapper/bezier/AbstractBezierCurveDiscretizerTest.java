package com.fredericboisguerin.pdf.wrapper.bezier;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;
import com.fredericboisguerin.pdf.wrapper.bezier.samples.BezierCurveConcaveTop;
import com.fredericboisguerin.pdf.wrapper.bezier.samples.BezierCurveSimpleConcaveBottom;
import com.fredericboisguerin.pdf.wrapper.bezier.samples.BezierCurveSimpleInflexionUp;
import com.fredericboisguerin.utils.MyCustomMatchers;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;

public abstract class AbstractBezierCurveDiscretizerTest {

    private BezierCurve bezierCurve;

    @Test
    public void should_contains_P0_when_BezierCurveConcaveTop() {
        bezierCurve = new BezierCurveConcaveTop();
        assertThatDrawingPointsContainP0();
    }

    @Test
    public void should_contains_P3_when_BezierCurveConcaveTop() {
        bezierCurve = new BezierCurveConcaveTop();
        assertThatDrawingPointsContainP3();
    }

    @Test
    public void should_contains_P0_when_BezierCurveSimpleConcaveBottom() {
        bezierCurve = new BezierCurveSimpleConcaveBottom();
        assertThatDrawingPointsContainP0();
    }

    @Test
    public void should_contains_P3_when_BezierCurveSimpleConcaveBottom() {
        bezierCurve = new BezierCurveSimpleConcaveBottom();
        assertThatDrawingPointsContainP3();
    }

    @Test
    public void should_contains_P0_when_BezierCurveSimpleInflexionUp() {
        bezierCurve = new BezierCurveSimpleInflexionUp();
        assertThatDrawingPointsContainP0();
    }

    @Test
    public void should_contains_P3_when_BezierCurveSimpleInflexionUp() {
        bezierCurve = new BezierCurveSimpleInflexionUp();
        assertThatDrawingPointsContainP3();
    }

    private void assertThatDrawingPointsContainP0() {
        assertThatDrawingPointsContain(getP0());
    }

    private void assertThatDrawingPointsContainP3() {
        assertThatDrawingPointsContain(getP3());
    }

    private void assertThatDrawingPointsContain(DrawingPoint p3) {
        List<DrawingPoint> drawingPoints = getDrawingPointsForBezierCurveDiscretizer();

        assertThat(drawingPoints, MyCustomMatchers.contains(p3));
    }

    private List<DrawingPoint> getDrawingPointsForBezierCurveDiscretizer() {
        BezierCurveDiscretizer curveDiscretizer = getAbstractBezierDiscretizer();
        return curveDiscretizer.getDrawingPointsForBezierCurve(bezierCurve);
    }

    protected abstract BezierCurveDiscretizer getAbstractBezierDiscretizer();

    private DrawingPoint getP0() {
        return bezierCurve.getP0();
    }

    private DrawingPoint getP3() {
        return bezierCurve.getP3();
    }
}