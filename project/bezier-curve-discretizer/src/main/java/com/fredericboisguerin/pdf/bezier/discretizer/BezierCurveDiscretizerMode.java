package com.fredericboisguerin.pdf.bezier.discretizer;

import java.util.function.Supplier;

public enum BezierCurveDiscretizerMode {
    FIXED_STEP_10_RANGES(() -> new BezierCurveDiscretizerFixedStep(Constants.TEN_POINTS)),
    ANGLE_THRESHOLD_1(() -> new BezierCurveDiscretizerWithAngleThresholdV1(Constants.ANGLE_THRESHOLD)),
    ANGLE_THRESHOLD_2(() -> new BezierCurveDiscretizerWithAngleThresholdV2(Constants.ANGLE_THRESHOLD));

    private final Supplier<BezierCurveDiscretizer> supplier;

    BezierCurveDiscretizerMode(Supplier<BezierCurveDiscretizer> supplier) {
        this.supplier = supplier;
    }

    Supplier<BezierCurveDiscretizer> getSupplier() {
        return supplier;
    }

    private static class Constants {
        static final int ANGLE_THRESHOLD = 90;
        static final int TEN_POINTS = 10;
    }
}
