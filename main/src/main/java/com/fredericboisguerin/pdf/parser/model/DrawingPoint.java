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
        return String.format("[ %s ; %s]", formatCoord(x), formatCoord(y));
    }

    private static String formatCoord(double coord) {
        String preFormat = String.format("%.2f", coord);
        return String.format("%6s", preFormat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrawingPoint that = (DrawingPoint) o;

        if (Float.compare(that.x, x) != 0) return false;
        return Float.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
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
