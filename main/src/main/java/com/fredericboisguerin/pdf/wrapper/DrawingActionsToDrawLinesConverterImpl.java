package com.fredericboisguerin.pdf.wrapper;

import com.fredericboisguerin.pdf.parser.math.BezierCurveDiscretizer;
import com.fredericboisguerin.pdf.parser.math.BezierCurveDiscretizerFactory;
import com.fredericboisguerin.pdf.parser.model.*;

import java.util.List;

/**
 * Created by fred on 10/01/17.
 */
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
            currentDrawLine = new DrawLine();
            drawLines.add(currentDrawLine);
            addPathPoint(moveTo.getDestination());
        }

        @Override
        public void visit(CurveTo curveTo) {
            List<DrawingPoint> drawingPoints = getDrawingPointsForBezierCurve(currentDrawingPoint, curveTo.getP1(), curveTo.getP2(), curveTo.getP3());
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
        BezierCurveDiscretizer build = bezierCurveDiscretizerFactory.build();
        return build.getDrawingPointsForBezierCurve(currentDrawingPoint, p1, p2, p3);
    }
}
