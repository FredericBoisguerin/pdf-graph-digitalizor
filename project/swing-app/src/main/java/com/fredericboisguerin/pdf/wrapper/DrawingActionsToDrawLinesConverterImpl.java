package com.fredericboisguerin.pdf.wrapper;

import com.fredericboisguerin.pdf.parser.model.*;
import com.fredericboisguerin.pdf.bezier.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.fredericboisguerin.pdf.bezier.BezierCurveDiscretizerMode.FIXED_STEP_10_RANGES;

public class DrawingActionsToDrawLinesConverterImpl implements DrawingActionsToDrawLinesConverter {

    @Override
    public DrawLines convert(List<DrawingAction> drawingActions) {
        InnerDrawingActionsConverter innerDrawingActionsConverter = new InnerDrawingActionsConverter();
        drawingActions.forEach(drawingAction -> drawingAction.accept(innerDrawingActionsConverter));
        return innerDrawingActionsConverter.drawLines;
    }

    private static class InnerDrawingActionsConverter implements DrawingActionVisitor {

        private final DrawLines drawLines = new DrawLines();
        private DrawLine currentDrawLine;
        private DrawingPoint currentDrawingPoint;

        @Override
        public void visit(MoveTo moveTo) {
            DrawingPoint destination = moveTo.getDestination();
            if (!destination.equals(currentDrawingPoint)) {
                currentDrawLine = new DrawLine(destination);
                drawLines.add(currentDrawLine);
                currentDrawingPoint = destination;
            }
        }

        @Override
        public void visit(CurveTo curveTo) {
            List<DrawingPoint> drawingPoints = getDrawingPointsForBezierCurve(currentDrawingPoint, curveTo
                    .getP1(), curveTo.getP2(), curveTo.getP3());
            drawingPoints.forEach(this::addPathPoint);
        }

        @Override
        public void visit(LineTo lineTo) {
            if (currentDrawLine == null) {
                throw new IllegalStateException("Must have MoveTo before LineTo");
            }
            addPathPoint(lineTo.getDestination());
        }

        private void addPathPoint(DrawingPoint pathPoint) {
            currentDrawLine.add(pathPoint);
            currentDrawingPoint = pathPoint;
        }
    }

    private static List<DrawingPoint> getDrawingPointsForBezierCurve(DrawingPoint currentDrawingPoint, DrawingPoint p1, DrawingPoint p2, DrawingPoint p3) {
        BezierCurveDiscretizerFactory bezierCurveDiscretizerFactory = new BezierCurveDiscretizerFactory();
        BezierCurveDiscretizer build = bezierCurveDiscretizerFactory.build(FIXED_STEP_10_RANGES);
        BezierCurve bezierCurve = new BezierCurve(convert(currentDrawingPoint), convert(p1), convert(p2), convert(p3));
        return build.getDrawingPointsForBezierCurve(bezierCurve)
                    .stream()
                    .map(DrawingActionsToDrawLinesConverterImpl::convertInverse)
                    .collect(Collectors.toList());
    }

    private static DrawingPoint convertInverse(BezierCurvePoint bezierCurvePoint) {
        return new DrawingPoint(bezierCurvePoint.getX(), bezierCurvePoint
                .getY());
    }

    private static BezierCurvePoint convert(DrawingPoint currentDrawingPoint) {
        return new BezierCurvePoint(currentDrawingPoint.getX(), currentDrawingPoint
                .getY());
    }
}
