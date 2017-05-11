package com.fredericboisguerin.pdf.graph;

public class XYPoint {
    private final Coord x;
    private final Coord y;

    public XYPoint(Coord x, Coord y) {
        this.x = x;
        this.y = y;
    }

    public XYPoint(float x, float y) {
        this(Coord.of(x), Coord.of(y));
    }

    public Coord getX() {
        return x;
    }

    public Coord getY() {
        return y;
    }
}
