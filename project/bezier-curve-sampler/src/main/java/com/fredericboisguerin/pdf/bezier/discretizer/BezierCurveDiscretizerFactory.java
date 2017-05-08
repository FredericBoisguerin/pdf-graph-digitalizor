package com.fredericboisguerin.pdf.bezier.discretizer;

public class BezierCurveDiscretizerFactory {

    public BezierCurveDiscretizer build(BezierCurveDiscretizerMode mode) {
        return mode.getSupplier().get();
    }
}
