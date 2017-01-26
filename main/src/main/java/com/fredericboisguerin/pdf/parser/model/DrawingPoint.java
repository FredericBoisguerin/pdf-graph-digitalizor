package com.fredericboisguerin.pdf.parser.model;

/**
 * Created by fred on 10/01/17.
 */
public class DrawingPoint implements Comparable<DrawingPoint> {
    private final float x;
    private final float y;

    public DrawingPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("[ %5.2f ; %5.2f]", x, y);
    }

    @Override
    public int compareTo(DrawingPoint other) {
        int compareX = Float.compare(this.x, other.x);
        if (compareX != 0) {
            return compareX;
        }
        return Float.compare(this.y, other.y);
    }
}
