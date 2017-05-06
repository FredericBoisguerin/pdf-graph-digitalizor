package com.fredericboisguerin.pdf.bezier;

public class BezierCurveDiscretizerFactory {

    public BezierCurveDiscretizer build(BezierCurveDiscretizerMode mode) {
        return mode.getSupplier().get();
    }
}
