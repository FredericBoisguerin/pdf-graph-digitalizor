package com.fredericboisguerin.pdf.bezier.discretizer;

import com.fredericboisguerin.pdf.bezier.model.BezierCurve;
import com.fredericboisguerin.pdf.bezier.model.BezierCurvePoint;

import java.util.ArrayList;
import java.util.List;

class BezierCurveDiscretizerFixedStep implements BezierCurveDiscretizer {
    private final int nbPointsInTRange;

    BezierCurveDiscretizerFixedStep(int nbPointsInTRange) {
        this.nbPointsInTRange = nbPointsInTRange;
    }

    @Override
    public List<BezierCurvePoint> getDrawingPointsForBezierCurve(BezierCurve bezierCurve) {
        List<BezierCurvePoint> result = new ArrayList<>();
        for (int i = 0; i < nbPointsInTRange; i++) {
            float t = 1f * i / (nbPointsInTRange - 1);
            BezierCurvePoint drawingPointForT = bezierCurve.getDrawingPointForT(t);
            result.add(drawingPointForT);
        }
        return result;
    }
}
