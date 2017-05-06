package com.fredericboisguerin.pdf.wrapper.bezier;

import java.util.ArrayList;
import java.util.List;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

class BezierCurveDiscretizerFixedStep implements BezierCurveDiscretizer {
    private final int nbPointsInTRange;

    BezierCurveDiscretizerFixedStep(int nbPointsInTRange) {
        this.nbPointsInTRange = nbPointsInTRange;
    }

    @Override
    public List<DrawingPoint> getDrawingPointsForBezierCurve(BezierCurve bezierCurve) {
        List<DrawingPoint> result = new ArrayList<>();
        for (int i = 0; i < nbPointsInTRange; i++) {
            float t = 1f * i / (nbPointsInTRange - 1);
            DrawingPoint drawingPointForT = bezierCurve.getDrawingPointForT(t);
            result.add(drawingPointForT);
        }
        return result;
    }
}
