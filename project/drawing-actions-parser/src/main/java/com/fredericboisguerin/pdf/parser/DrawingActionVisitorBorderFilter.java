package com.fredericboisguerin.pdf.parser;

import com.fredericboisguerin.pdf.parser.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DrawingActionVisitorBorderFilter {

    private final BorderPoints borderPoints;

    DrawingActionVisitorBorderFilter(BorderPoints borderPoints) {
        this.borderPoints = borderPoints;
    }

    List<DrawingAction> getFilteredActions(List<DrawingAction> actionList) {
        DrawingActionBorderFilter drawingActionBorderFilter = new DrawingActionBorderFilter(borderPoints);
        actionList.forEach(drawingAction -> drawingAction.accept(drawingActionBorderFilter));
        return drawingActionBorderFilter.getFilteredDrawingActions();
    }

    private class DrawingActionBorderFilter implements DrawingActionVisitor {

        private final BorderPoints borderPoints;
        private final List<DrawingAction> filteredDrawingActions = new ArrayList<>();
        private final List<DrawingAction> lastActionsSinceMoveTo = new ArrayList<>();
        private boolean waitForNextMoveTo;

        public DrawingActionBorderFilter(BorderPoints borderPoints) {
            this.borderPoints = borderPoints;
        }

        @Override
        public void visit(MoveTo moveTo) {
            flushLastActions();
            recordDrawingActionWithCondition(moveTo);
        }

        private void recordDrawingActionWithCondition(DrawingAction action) {
            if (!waitForNextMoveTo && action.isContainedIn(borderPoints)) {
                lastActionsSinceMoveTo.add(action);
            } else {
                waitForNextMoveTo();
            }
        }

        void waitForNextMoveTo() {
            waitForNextMoveTo = true;
            lastActionsSinceMoveTo.clear();
        }

        void flushLastActions() {
            waitForNextMoveTo = false;
            filteredDrawingActions.addAll(lastActionsSinceMoveTo);
            lastActionsSinceMoveTo.clear();
        }

        @Override
        public void visit(LineTo lineTo) {
            recordDrawingActionWithCondition(lineTo);
        }

        @Override
        public void visit(CurveTo curveTo) {
            recordDrawingActionWithCondition(curveTo);
        }

        List<DrawingAction> getFilteredDrawingActions() {
            flushLastActions();
            return filteredDrawingActions;
        }
    }
}
