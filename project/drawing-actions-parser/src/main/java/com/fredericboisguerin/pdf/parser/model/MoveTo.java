package com.fredericboisguerin.pdf.parser.model;

import com.fredericboisguerin.pdf.parser.BorderPoints;

public class MoveTo implements DrawingAction {

    private final DrawingPoint destination;

    public MoveTo(DrawingPoint destination) {
        this.destination = destination;
    }

    public DrawingPoint getDestination() {
        return destination;
    }

    @Override
    public void accept(DrawingActionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isContainedIn(BorderPoints borderPoints) {
        return borderPoints.contains(destination);
    }
}
