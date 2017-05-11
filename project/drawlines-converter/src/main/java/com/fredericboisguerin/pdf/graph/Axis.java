package com.fredericboisguerin.pdf.graph;

public abstract class Axis {
    private final Coord min;
    private final Coord max;

    public Axis(Coord min, Coord max) {
        this.min = min;
        this.max = max;
    }

    public Coord getMin() {
        return min;
    }

    public Coord getMax() {
        return max;
    }

    public float getLength() {
        return max.diff(min);
    }

    @Override
    public String toString() {
        return "Axis{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    public abstract void accept(AxisVisitor visitor);
}
