package com.fredericboisguerin.pdf.wrapper.bezier;

public class BezierCurveDiscretizerWithAngleThresholdV1Test extends AbstractBezierCurveDiscretizerTest {

    @Override
    protected BezierCurveDiscretizer getAbstractBezierDiscretizer() {
        return new BezierCurveDiscretizerWithAngleThresholdV1(90);
    }
}