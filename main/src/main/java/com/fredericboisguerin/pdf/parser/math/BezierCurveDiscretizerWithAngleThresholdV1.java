package com.fredericboisguerin.pdf.parser.math;

import com.fredericboisguerin.pdf.parser.model.BezierCurve;

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
