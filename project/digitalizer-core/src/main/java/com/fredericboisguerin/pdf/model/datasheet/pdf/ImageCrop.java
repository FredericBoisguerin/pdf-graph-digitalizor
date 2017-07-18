package com.fredericboisguerin.pdf.model.datasheet.pdf;

import com.fredericboisguerin.pdf.parser.BorderPoints;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

public class ImageCrop {
    private final ImageCropPoint lowerLeft;
    private final ImageCropPoint upperRight;

    public ImageCrop(ImageCropPoint lowerLeft, ImageCropPoint upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    public static ImageCrop noCrop() {
        return new ImageCrop(ImageCropPoint.lowestLeft(), ImageCropPoint.uppestRight());
    }

    public BorderPoints applyTo(BorderPoints borderPoints) {
        DrawingPoint lowerLeft = this.lowerLeft.applyTo(borderPoints);
        DrawingPoint upperRight = this.upperRight.applyTo(borderPoints);
        return new BorderPoints(lowerLeft, upperRight);
    }

    @Override
    public String toString() {
        return "lowerLeft=" + lowerLeft + ", upperRight=" + upperRight;
    }
}
