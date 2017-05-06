package com.fredericboisguerin.pdf.bezier;

public class BezierCurveDiscretizerWithAngleThresholdV2Test extends AbstractBezierCurveDiscretizerTest {

    @Override
    protected BezierCurveDiscretizer getAbstractBezierDiscretizer() {
        return new BezierCurveDiscretizerWithAngleThresholdV2(90);
    }
}