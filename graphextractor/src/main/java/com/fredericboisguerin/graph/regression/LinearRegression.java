package com.fredericboisguerin.graph.regression;

import com.fredericboisguerin.graph.model.input.DrawingPoint;

public class LinearRegression {

    private final float slope;
    private final float offset;

    public LinearRegression(DrawingPoint a, DrawingPoint b) {
        this.slope = (a.getY() - b.getY()) / (a.getX() - b.getX());
        this.offset = a.getY() - slope * a.getX();
    }

    public float getSlope() {
        return slope;
    }

    public float getOffset() {
        return offset;
    }

    public boolean includes(DrawingPoint drawingPoint) {
        float actual = drawingPoint.getY();
        float expected = drawingPoint.getX() * slope + offset;
        return Float.compare(actual, expected) == 0;
    }

    @Override
    public String toString() {
        String slopeStr = String.format("%.2f", slope);
        String offsetStr = String.format("%.2f", offset);
        return String.format("y = %s * x + %s", slopeStr, offsetStr);
    }
}
