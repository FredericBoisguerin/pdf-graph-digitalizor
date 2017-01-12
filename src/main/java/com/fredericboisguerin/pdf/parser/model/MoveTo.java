package com.fredericboisguerin.pdf.parser.model;

/**
 * Created by fred on 10/01/17.
 */
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
