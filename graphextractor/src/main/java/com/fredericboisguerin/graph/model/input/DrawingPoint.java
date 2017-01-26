package com.fredericboisguerin.graph.model.input;

import com.fredericboisguerin.graph.regression.LinearRegression;

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

    public static DrawingPoint of(float x, float y) {
        return new DrawingPoint(x, y);
    }

    public boolean isAlignedWith(DrawingPoint before, DrawingPoint after) {
        LinearRegression linearRegression = new LinearRegression(before, after);
        return linearRegression.includes(this);
    }
}
