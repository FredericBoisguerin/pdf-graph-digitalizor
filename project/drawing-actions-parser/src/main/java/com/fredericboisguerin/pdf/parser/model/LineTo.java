package com.fredericboisguerin.pdf.parser.model;

public class LineTo implements DrawingAction {

    private final DrawingPoint destination;

    public LineTo(DrawingPoint destination) {
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
