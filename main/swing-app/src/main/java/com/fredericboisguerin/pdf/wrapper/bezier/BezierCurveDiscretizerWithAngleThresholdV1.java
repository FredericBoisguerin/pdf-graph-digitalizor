package com.fredericboisguerin.pdf.wrapper.bezier;

class BezierCurveDiscretizerWithAngleThresholdV1
        extends AbstractBezierCurveDiscretizerWithAngleThreshold {

    protected BezierCurveDiscretizerWithAngleThresholdV1(int angleThreshold) {
        super(angleThreshold);
    }

    @Override
    protected float getNextT(float t) {
        return 1;
    }
}
