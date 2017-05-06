package com.fredericboisguerin.pdf.parser.model;

public class CurveTo implements DrawingAction {

    private final DrawingPoint p1;
    private final DrawingPoint p2;
    private final DrawingPoint p3;

    public CurveTo(DrawingPoint p1, DrawingPoint p2, DrawingPoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public DrawingPoint getP1() {
        return p1;
    }

    public DrawingPoint getP2() {
        return p2;
    }

    public DrawingPoint getP3() {
        return p3;
    }

    @Override
    public void accept(DrawingActionVisitor visitor) {
        visitor.visit(this);
    }
}
