package com.fredericboisguerin.graph.model.input;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DrawLineTest {

    private DrawLine drawLine;

    @Before
    public void setUp() {
        drawLine = new DrawLine();
    }

    @Test
    public void should_alone_point_return_not_redondant() {
        DrawingPoint startPoint = DrawingPoint.of(0, 0);
        drawLine.add(startPoint);

        assertFalse(drawLine.hasRedondant(startPoint));
    }

    @Test
    public void should_first_point_return_not_redondant() {
        DrawingPoint startPoint = DrawingPoint.of(0, 0);
        drawLine.add(startPoint);
        drawLine.add(DrawingPoint.of(1, 1));
        drawLine.add(DrawingPoint.of(2, 2));

        assertFalse(drawLine.hasRedondant(startPoint));
    }

    @Test
    public void should_last_point_return_not_redondant() {
        drawLine.add(DrawingPoint.of(0, 0));
        drawLine.add(DrawingPoint.of(1, 1));
        DrawingPoint endPoint = DrawingPoint.of(2, 2);
        drawLine.add(endPoint);

        assertFalse(drawLine.hasRedondant(endPoint));

    }

    @Test
    public void should_aligned_centered_point_return_redondant() {
        drawLine.add(DrawingPoint.of(0, 0));
        DrawingPoint centeredPoint = DrawingPoint.of(1, 1);
        drawLine.add(centeredPoint);
        drawLine.add(DrawingPoint.of(2, 2));

        assertTrue(drawLine.hasRedondant(centeredPoint));
    }
}