package com.fredericboisguerin.pdf.parser;

import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

public class BorderPoints {
    private final DrawingPoint lowerLeft;
    private final DrawingPoint upperRight;

    public BorderPoints(DrawingPoint lowerLeft, DrawingPoint upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public DrawingPoint getLowerLeft() {
        return lowerLeft;
    }

    public DrawingPoint getUpperRight() {
        return upperRight;
    }
}
