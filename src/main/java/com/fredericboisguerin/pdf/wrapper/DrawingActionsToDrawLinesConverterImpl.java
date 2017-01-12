package com.fredericboisguerin.pdf.wrapper;

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
        private DrawLine current;

        @Override
        public void visit(MoveTo moveTo) {
            current = new DrawLine();
            drawLines.add(current);
            current.add(moveTo.getDestination());
        }

        @Override
        public void visit(LineTo lineTo) {
            if (current == null) {
                throw new IllegalStateException("Must have MoveTo before LineTo");
            }
            current.add(lineTo.getDestination());
        }
    }
}
