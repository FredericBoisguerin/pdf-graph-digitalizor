package com.fredericboisguerin.pdf.model.datasheet.pdf;

public class ImageCropPointCoord {

    private static final float LOWEST_RATIO_VALUE = 0f;
    private static final float BIGGEST_RATIO_VALUE = 1f;

    private final float ratio;

    public ImageCropPointCoord(float ratio) {
        assert 0 <= ratio && ratio <= 1;
        this.ratio = ratio;
    }

    public static ImageCropPointCoord biggestRatio() {
        return new ImageCropPointCoord(BIGGEST_RATIO_VALUE);
    }

    public static ImageCropPointCoord lowestRatio() {
        return new ImageCropPointCoord(LOWEST_RATIO_VALUE);
    }

    public float applyTo(float coord) {
        return coord * ratio;
    }
}
