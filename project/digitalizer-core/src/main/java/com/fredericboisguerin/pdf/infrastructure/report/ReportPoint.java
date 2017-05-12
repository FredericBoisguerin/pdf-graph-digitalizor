package com.fredericboisguerin.pdf.infrastructure.report;

public class ReportPoint {

    private final float x;
    private final float y;

    public ReportPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
