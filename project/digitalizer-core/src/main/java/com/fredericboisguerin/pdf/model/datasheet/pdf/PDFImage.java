package com.fredericboisguerin.pdf.model.datasheet.pdf;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PDFImage {
    private final byte[] buf;
    private final Dimension width;
    private final Dimension height;

    public PDFImage(byte[] buf, int width, int height) {
        this.buf = buf;
        this.width = Dimension.fromPixels(width);
        this.height = Dimension.fromPixels(height);
    }

    public Dimension getWidth() {
        return width;
    }

    public Dimension getHeight() {
        return height;
    }

    public InputStream asInputStream() {
        return new ByteArrayInputStream(buf);
    }
}
