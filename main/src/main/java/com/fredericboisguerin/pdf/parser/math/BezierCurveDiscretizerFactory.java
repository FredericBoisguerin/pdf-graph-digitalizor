package com.fredericboisguerin.pdf.parser.math;

/**
 * Created by fred on 31/01/17.
 */
public class BezierCurveDiscretizerFactory {

    public static final int ANGLE_THRESHOLD_IN_DEGREES = 179;

    public BezierCurveDiscretizer build() {
        return new BezierCurveDiscretizerWithAngleThreshold(ANGLE_THRESHOLD_IN_DEGREES);
    }
}
