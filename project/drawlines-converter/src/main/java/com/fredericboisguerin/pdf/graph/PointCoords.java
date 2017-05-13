package com.fredericboisguerin.pdf.graph;

public class PointCoords {
    private final Coord x;
    private final Coord y;

    public PointCoords(Coord x, Coord y) {
        this.x = x;
        this.y = y;
    }

    public PointCoords(float x, float y) {
        this(Coord.of(x), Coord.of(y));
    }

    public Coord getX() {
        return x;
    }

    public Coord getY() {
        return y;
    }
}
