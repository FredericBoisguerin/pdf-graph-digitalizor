package com.fredericboisguerin.pdf.wrapper.bezier;

public class BezierCurveDiscretizerFactory {

    public BezierCurveDiscretizer build() {
        return new BezierCurveDiscretizerFixedStep(11);
    }
}
