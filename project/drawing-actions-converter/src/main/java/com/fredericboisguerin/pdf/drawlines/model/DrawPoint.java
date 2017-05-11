package com.fredericboisguerin.pdf.drawlines.model;

public class DrawPoint implements Comparable<DrawPoint> {
    private final float x;
    private final float y;

    public DrawPoint(float x, float y) {
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

        DrawPoint that = (DrawPoint) o;

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
    public int compareTo(DrawPoint other) {
        int compareX = Float.compare(this.x, other.x);
        if (compareX != 0) {
            return compareX;
        }
        return Float.compare(this.y, other.y);
    }
}
