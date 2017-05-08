package com.fredericboisguerin.pdf.bezier.discretizer;

public class BezierCurveDiscretizerWithAngleThresholdV1Test extends AbstractBezierCurveDiscretizerTest {

    @Override
    protected BezierCurveDiscretizer getAbstractBezierDiscretizer() {
        return new BezierCurveDiscretizerWithAngleThresholdV1(90);
    }
}