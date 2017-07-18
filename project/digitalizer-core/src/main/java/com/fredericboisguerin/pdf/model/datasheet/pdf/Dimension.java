package com.fredericboisguerin.pdf.model.datasheet.pdf;

public class Dimension {
    private final int pixels;

    private Dimension(int pixels) {
        this.pixels = pixels;
    }

    public static Dimension fromPixels(int pixels) {
        return new Dimension(pixels);
    }

    public int inPixels() {
        return pixels;
    }
}
