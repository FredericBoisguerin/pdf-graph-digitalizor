package com.fredericboisguerin.pdf.wrapper.bezier;

/**
 * Created by fred on 31/01/17.
 */
public class BezierCurveDiscretizerFactory {

    public BezierCurveDiscretizer build() {
        return new BezierCurveDiscretizerFixedStep(11);
    }
}
