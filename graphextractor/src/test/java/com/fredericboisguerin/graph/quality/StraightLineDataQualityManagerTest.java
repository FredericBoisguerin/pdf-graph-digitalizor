package com.fredericboisguerin.graph.quality;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fredericboisguerin.graph.model.input.DrawLine;
import com.fredericboisguerin.graph.model.input.DrawingPoint;

public class StraightLineDataQualityManagerTest {

    private DataQualityManager<DrawLine> qualityManager;
    private DrawLine drawLine;

    @Before
    public void setUp() {
        drawLine = new DrawLine();
        qualityManager = new StraightLineDataQualityManager();
    }

    @Test
    public void should_clean_all_intermediate_points_when_straight_line() {
        DrawingPoint startPoint = DrawingPoint.of(0, 0);
        drawLine.add(startPoint);
        drawLine.add(DrawingPoint.of(1, 1));
        DrawingPoint endPoint = DrawingPoint.of(2, 2);
        drawLine.add(endPoint);

        DrawLine drawLine = qualityManager.cleanData(this.drawLine);

        assertEquals(2, drawLine.size());
        assertTrue(drawLine.containsAll(startPoint, endPoint));
    }

    @Test
    public void should_clean_all_aligned_points_when_first_part_is_straight_line() {
        DrawingPoint startPoint = DrawingPoint.of(0, 0);
        drawLine.add(startPoint);
        drawLine.add(DrawingPoint.of(1, 1));
        DrawingPoint beforeEndPoint = DrawingPoint.of(2, 2);
        drawLine.add(beforeEndPoint);
        DrawingPoint endPoint = DrawingPoint.of(5, 0);
        drawLine.add(endPoint);

        DrawLine drawLine = qualityManager.cleanData(this.drawLine);

        assertEquals(3, drawLine.size());
        assertTrue(drawLine.containsAll(startPoint, beforeEndPoint, endPoint));
    }
}