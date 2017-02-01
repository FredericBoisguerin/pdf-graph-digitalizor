package com.fredericboisguerin.pdf.parser.math;

class BezierCurveDiscretizerWithAngleThresholdV2
        extends AbstractBezierCurveDiscretizerWithAngleThreshold {

    protected BezierCurveDiscretizerWithAngleThresholdV2(int angleThreshold) {
        super(angleThreshold);
    }

    @Override
    protected float getNextT(float t) {
        return t < 0.5 ? 0.5f : 1f;
    }
}
