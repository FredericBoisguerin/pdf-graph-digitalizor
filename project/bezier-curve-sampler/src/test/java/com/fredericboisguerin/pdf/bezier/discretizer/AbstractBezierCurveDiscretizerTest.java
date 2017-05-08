package com.fredericboisguerin.pdf.bezier.discretizer;

import com.fredericboisguerin.pdf.bezier.model.BezierCurve;
import com.fredericboisguerin.pdf.bezier.model.BezierCurvePoint;
import com.fredericboisguerin.pdf.bezier.samples.BezierCurveConcaveTop;
import com.fredericboisguerin.pdf.bezier.samples.BezierCurveSimpleConcaveBottom;
import com.fredericboisguerin.pdf.bezier.samples.BezierCurveSimpleInflexionUp;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

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

    private void assertThatDrawingPointsContain(BezierCurvePoint point) {
        List<BezierCurvePoint> drawingPoints = getDrawingPointsForBezierCurveDiscretizer();

        assertTrue(drawingPoints.contains(point));
    }

    private List<BezierCurvePoint> getDrawingPointsForBezierCurveDiscretizer() {
        BezierCurveDiscretizer curveDiscretizer = getAbstractBezierDiscretizer();
        return curveDiscretizer.getDrawingPointsForBezierCurve(bezierCurve);
    }

    protected abstract BezierCurveDiscretizer getAbstractBezierDiscretizer();

    private BezierCurvePoint getP0() {
        return bezierCurve.getP0();
    }

    private BezierCurvePoint getP3() {
        return bezierCurve.getP3();
    }
}