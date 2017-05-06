package com.fredericboisguerin.pdf.parser.model;

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
}
