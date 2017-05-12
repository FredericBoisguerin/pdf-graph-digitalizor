package com.fredericboisguerin.pdf.infrastructure.wrapper;

import java.util.Comparator;

public class CoordComparator implements Comparator<Float> {

    private static final float RATIO_TOLERANCE = 1e-4f;

    @Override
    public int compare(Float c1, Float c2) {
        if (areEqual(c1, c2)) {
            return 0;
        }
        return Float.compare(c1, c2);
    }

    private boolean areEqual(Float c1, Float c2) {
        float dist = Math.abs(c2 - c1);
        float minAbs = Math.min(c1, c2);
        float ratio = dist / minAbs;
        return Float.compare(ratio, RATIO_TOLERANCE) < 0;
    }

}
