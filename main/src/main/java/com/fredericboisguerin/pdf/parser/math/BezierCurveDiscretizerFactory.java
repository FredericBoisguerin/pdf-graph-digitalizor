package com.fredericboisguerin.pdf.parser.math;

/**
 * Created by fred on 31/01/17.
 */
public class BezierCurveDiscretizerFactory {

    public BezierCurveDiscretizer build() {
        return new BezierCurveDiscretizerFixedStep(11);
    }
}
