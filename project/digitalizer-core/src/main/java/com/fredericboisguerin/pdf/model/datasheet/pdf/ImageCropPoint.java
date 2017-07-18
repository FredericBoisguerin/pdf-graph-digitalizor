package com.fredericboisguerin.pdf.model.datasheet.pdf;

import com.fredericboisguerin.pdf.parser.BorderPoints;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

public class ImageCropPoint {
    private final ImageCropPointCoord xRatio;
    private final ImageCropPointCoord yRatio;

    public ImageCropPoint(ImageCropPointCoord xRatio, ImageCropPointCoord yRatio) {
        this.xRatio = xRatio;
        this.yRatio = yRatio;
    }

    public static ImageCropPoint uppestRight() {
        return new ImageCropPoint(ImageCropPointCoord.biggestRatio(), ImageCropPointCoord.biggestRatio());
    }

    public static ImageCropPoint lowestLeft() {
        return new ImageCropPoint(ImageCropPointCoord.lowestRatio(), ImageCropPointCoord.lowestRatio());
    }

    public DrawingPoint applyTo(BorderPoints drawingPoint) {
        DrawingPoint lowerLeft = drawingPoint.getLowerLeft();
        DrawingPoint upperRight = drawingPoint.getUpperRight();
        float x = xRatio.applyTo(upperRight.getX() - lowerLeft.getX());
        float y = yRatio.applyTo(upperRight.getY() - lowerLeft.getY());
        return new DrawingPoint(x, y);
    }

    @Override
    public String toString() {
        return "[" + xRatio + "," + yRatio + "]";
    }
}

