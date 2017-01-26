package com.fredericboisguerin.graph.regression;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fredericboisguerin.graph.model.input.DrawingPoint;

public class LinearRegressionTest {

    public static final double ABSOLUTE_TOLERANCE = 1e-12;

    @Test
    public void should_return_slope_1_and_offset_1() {
        DrawingPoint a = DrawingPoint.of(1,2);
        DrawingPoint b = DrawingPoint.of(2,3);

        LinearRegression linearRegression = new LinearRegression(a, b);

        assertEquals(1, linearRegression.getSlope(), ABSOLUTE_TOLERANCE);
        assertEquals(1, linearRegression.getOffset(), ABSOLUTE_TOLERANCE);
    }

    @Test
    public void should_return_correct_slope_1_and_offset_0() {
        DrawingPoint a = DrawingPoint.of(1,2);
        DrawingPoint b = DrawingPoint.of(2,4);

        LinearRegression linearRegression = new LinearRegression(a, b);

        assertEquals(2, linearRegression.getSlope(), ABSOLUTE_TOLERANCE);
        assertEquals(0, linearRegression.getOffset(), ABSOLUTE_TOLERANCE);
    }

}