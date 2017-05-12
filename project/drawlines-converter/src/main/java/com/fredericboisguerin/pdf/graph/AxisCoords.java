package com.fredericboisguerin.pdf.graph;

public class AxisCoords {
    private final Coord min;
    private final Coord max;

    public AxisCoords(Coord min, Coord max) {
        this.min = min;
        this.max = max;
    }

    public Coord getMin() {
        return min;
    }

    public Coord getMax() {
        return max;
    }
}
