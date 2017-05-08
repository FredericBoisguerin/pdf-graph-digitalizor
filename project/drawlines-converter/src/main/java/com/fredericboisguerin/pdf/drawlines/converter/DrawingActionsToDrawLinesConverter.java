package com.fredericboisguerin.pdf.drawlines.converter;

import com.fredericboisguerin.pdf.bezier.discretizer.BezierCurveDiscretizer;
import com.fredericboisguerin.pdf.bezier.discretizer.BezierCurveDiscretizerFactory;
import com.fredericboisguerin.pdf.bezier.model.BezierCurve;
import com.fredericboisguerin.pdf.bezier.model.BezierCurvePoint;
import com.fredericboisguerin.pdf.drawlines.model.DrawLine;
import com.fredericboisguerin.pdf.drawlines.model.DrawLines;
import com.fredericboisguerin.pdf.parser.model.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.fredericboisguerin.pdf.bezier.discretizer.BezierCurveDiscretizerMode.FIXED_STEP_10_RANGES;

public class DrawingActionsToDrawLinesConverter {

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
        BezierCurveDiscretizerFactory discretizerFactory = new BezierCurveDiscretizerFactory();
        BezierCurveDiscretizer discretizer = discretizerFactory.build(FIXED_STEP_10_RANGES);
        BezierCurve bezierCurve = new BezierCurve(toBezierCurvePoint(currentDrawingPoint), toBezierCurvePoint(p1), toBezierCurvePoint(p2), toBezierCurvePoint(p3));
        return discretizer.getDrawingPointsForBezierCurve(bezierCurve)
                          .stream()
                          .map(DrawingActionsToDrawLinesConverter::toDrawingPoint)
                          .collect(Collectors.toList());
    }

    private static DrawingPoint toDrawingPoint(BezierCurvePoint bezierCurvePoint) {
        return new DrawingPoint(bezierCurvePoint.getX(), bezierCurvePoint
                .getY());
    }

    private static BezierCurvePoint toBezierCurvePoint(DrawingPoint currentDrawingPoint) {
        return new BezierCurvePoint(currentDrawingPoint.getX(), currentDrawingPoint
                .getY());
    }
}
