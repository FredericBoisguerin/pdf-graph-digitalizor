package com.fredericboisguerin.graph.model.input;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DrawingPointTest {

    @Test
    public void should_return_aligned_when_aligned_x_and_y() {
        DrawingPoint before = DrawingPoint.of(0, 0);
        DrawingPoint after = DrawingPoint.of(2, 2);
        DrawingPoint drawingPoint = DrawingPoint.of(1, 1);

        assertTrue(drawingPoint.isAlignedWith(before, after));
    }

    @Test
    public void should_return_not__aligned_when_not_aligned_x_and_y() {
        DrawingPoint before = DrawingPoint.of(0, 0);
        DrawingPoint after = DrawingPoint.of(2, 1);
        DrawingPoint drawingPoint = DrawingPoint.of(1, 1);

        assertFalse(drawingPoint.isAlignedWith(before, after));
    }

}