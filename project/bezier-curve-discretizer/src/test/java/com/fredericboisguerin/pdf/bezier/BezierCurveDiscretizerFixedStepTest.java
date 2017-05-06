package com.fredericboisguerin.pdf.bezier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import com.fredericboisguerin.pdf.bezier.BezierCurve;
import com.fredericboisguerin.pdf.bezier.BezierCurveDiscretizer;
import com.fredericboisguerin.pdf.bezier.BezierCurveDiscretizerFixedStep;
import com.fredericboisguerin.pdf.bezier.samples.BezierCurveSimpleConcaveBottom;
import com.fredericboisguerin.pdf.bezier.samples.BezierCurveSimpleConcaveBottomReverse;
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

        List<BezierCurvePoint> drawingPointsForBezierCurve = bezierCurveDiscretizer.getDrawingPointsForBezierCurve(
                concaveBottom);

        assertThat(drawingPointsForBezierCurve.size(), is(NB_POINTS_IN_T_RANGE));
    }

    @Test
    public void should_contain_N_points_when_concave_bottom_reverse() {
        BezierCurveSimpleConcaveBottomReverse concaveBottomReverse = new BezierCurveSimpleConcaveBottomReverse();

        List<BezierCurvePoint> drawingPointsForBezierCurve = bezierCurveDiscretizer.getDrawingPointsForBezierCurve(
                concaveBottomReverse);

        assertThat(drawingPointsForBezierCurve.size(), is(NB_POINTS_IN_T_RANGE));
    }

    @Override
    protected BezierCurveDiscretizer getAbstractBezierDiscretizer() {
        return bezierCurveDiscretizer;
    }
}