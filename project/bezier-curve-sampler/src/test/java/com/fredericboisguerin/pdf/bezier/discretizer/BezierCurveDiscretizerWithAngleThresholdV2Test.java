package com.fredericboisguerin.pdf.bezier.discretizer;

public class BezierCurveDiscretizerWithAngleThresholdV2Test extends AbstractBezierCurveDiscretizerTest {

    @Override
    protected BezierCurveDiscretizer getAbstractBezierDiscretizer() {
        return new BezierCurveDiscretizerWithAngleThresholdV2(90);
    }
}