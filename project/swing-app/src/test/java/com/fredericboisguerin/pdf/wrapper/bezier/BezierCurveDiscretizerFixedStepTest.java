package com.fredericboisguerin.pdf.wrapper.bezier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;
import com.fredericboisguerin.pdf.wrapper.bezier.BezierCurve;
import com.fredericboisguerin.pdf.wrapper.bezier.BezierCurveDiscretizer;
import com.fredericboisguerin.pdf.wrapper.bezier.BezierCurveDiscretizerFixedStep;
import com.fredericboisguerin.pdf.wrapper.bezier.samples.BezierCurveSimpleConcaveBottom;
import com.fredericboisguerin.pdf.wrapper.bezier.samples.BezierCurveSimpleConcaveBottomReverse;
import org.junit.Before;
import org.junit.Test;

public class BezierCurveDiscretizerFixedStepTest extends AbstractBezierCurveDiscretizerTest {

    private static final int NB_POINTS_IN_T_RANGE = 11;

    private BezierCurveDiscretizer bezierCurveDiscretizer;

    @Before
    public void setUp() {
        bezierCurveDiscretizer = new BezierCurveDiscretizerFixedStep(NB_POINTS_IN_T_RANGE);
    }

    @Test
    public void should_contain_N_points_when_concave_bottom() {
        BezierCurveSimpleConcaveBottom concaveBottom = new BezierCurveSimpleConcaveBottom();

        List<DrawingPoint> drawingPointsForBezierCurve = bezierCurveDiscretizer.getDrawingPointsForBezierCurve(
                concaveBottom);

        assertThat(drawingPointsForBezierCurve.size(), is(NB_POINTS_IN_T_RANGE));
    }

    @Test
    public void should_contain_N_points_when_concave_bottom_reverse() {
        BezierCurveSimpleConcaveBottomReverse concaveBottomReverse = new BezierCurveSimpleConcaveBottomReverse();

        List<DrawingPoint> drawingPointsForBezierCurve = bezierCurveDiscretizer.getDrawingPointsForBezierCurve(
                concaveBottomReverse);

        assertThat(drawingPointsForBezierCurve.size(), is(NB_POINTS_IN_T_RANGE));
    }

    @Override
    protected BezierCurveDiscretizer getAbstractBezierDiscretizer() {
        return bezierCurveDiscretizer;
    }
}