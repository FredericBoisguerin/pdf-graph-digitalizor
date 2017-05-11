package com.fredericboisguerin.pdf.drawlines.converter;

import com.fredericboisguerin.pdf.drawlines.model.DrawLine;
import com.fredericboisguerin.pdf.drawlines.model.DrawLines;
import com.fredericboisguerin.pdf.drawlines.model.DrawPoint;
import com.fredericboisguerin.pdf.parser.model.DrawingAction;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;
import com.fredericboisguerin.pdf.parser.model.LineTo;
import com.fredericboisguerin.pdf.parser.model.MoveTo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class DrawingActionsToDrawLinesConverterTest {

    private static final double DELTA = 1e-12;

    private static final float xA = 0;
    private static final float yA = 1;
    private static final float xB = 2;
    private static final float yB = 3;
    private static final float xC = 4;
    private static final float yC = 5;
    private static final float xD = 6;
    private static final float yD = 7;

    private DrawingActionsToDrawLinesConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new DrawingActionsToDrawLinesConverter();
    }

    @Test
    public void should_have_one_series_with_three_points_after_1_moveTo_and_2_lineTo() throws Exception {
        List<DrawingAction> drawingActionList = new ArrayList<>();
        drawingActionList.add(new MoveTo(buildDrawPoint(xA, yA)));
        drawingActionList.add(new LineTo(buildDrawPoint(xB, yB)));
        drawingActionList.add(new LineTo(buildDrawPoint(xC, yC)));

        DrawLines series = converter.convert(drawingActionList);
        Iterator<DrawLine> drawLineIterator = series.stream().iterator();

        assertTrue(drawLineIterator.hasNext());
        DrawLine AC = drawLineIterator.next();
        assertFalse(drawLineIterator.hasNext());

        Iterator<DrawPoint> acIterator = AC.stream().iterator();
        assertTrue(acIterator.hasNext());
        DrawPoint A = acIterator.next();
        assertTrue(acIterator.hasNext());
        DrawPoint B = acIterator.next();
        assertTrue(acIterator.hasNext());
        DrawPoint C = acIterator.next();
        assertFalse(acIterator.hasNext());

        assertEquals(xA, A.getX(), DELTA);
        assertEquals(yA, A.getY(), DELTA);
        assertEquals(xB, B.getX(), DELTA);
        assertEquals(yB, B.getY(), DELTA);
        assertEquals(xC, C.getX(), DELTA);
        assertEquals(yC, C.getY(), DELTA);
    }

    @Test
    public void should_not_create_new_line_nor_new_point_when_moveTo_last_point() throws Exception {
        List<DrawingAction> drawingActionList = new ArrayList<>();
        drawingActionList.add(new MoveTo(buildDrawPoint(xA, yA)));
        drawingActionList.add(new LineTo(buildDrawPoint(xB, yB)));
        drawingActionList.add(new MoveTo(buildDrawPoint(xB, yB)));
        drawingActionList.add(new LineTo(buildDrawPoint(xC, yC)));

        DrawLines series = converter.convert(drawingActionList);
        Iterator<DrawLine> drawLineIterator = series.stream().iterator();

        assertTrue(drawLineIterator.hasNext());
        DrawLine AC = drawLineIterator.next();
        assertFalse(drawLineIterator.hasNext());

        Iterator<DrawPoint> acIterator = AC.stream().iterator();
        assertTrue(acIterator.hasNext());
        DrawPoint A = acIterator.next();
        assertTrue(acIterator.hasNext());
        DrawPoint B = acIterator.next();
        assertTrue(acIterator.hasNext());
        DrawPoint C = acIterator.next();
        assertFalse(acIterator.hasNext());

        assertEquals(xA, A.getX(), DELTA);
        assertEquals(yA, A.getY(), DELTA);
        assertEquals(xB, B.getX(), DELTA);
        assertEquals(yB, B.getY(), DELTA);
        assertEquals(xC, C.getX(), DELTA);
        assertEquals(yC, C.getY(), DELTA);
    }

    @Test
    public void should_have_two_series_with_two_points_after_two_moveTo_lineTo() throws Exception {
        List<DrawingAction> drawingActionList = new ArrayList<>();
        drawingActionList.add(new MoveTo(buildDrawPoint(xA, yA)));
        drawingActionList.add(new LineTo(buildDrawPoint(xB, yB)));
        drawingActionList.add(new MoveTo(buildDrawPoint(xC, yC)));
        drawingActionList.add(new LineTo(buildDrawPoint(xD, yD)));

        DrawLines series = converter.convert(drawingActionList);
        Iterator<DrawLine> drawLineIterator = series.stream().iterator();

        assertTrue(drawLineIterator.hasNext());
        DrawLine AB = drawLineIterator.next();
        assertTrue(drawLineIterator.hasNext());
        DrawLine CD = drawLineIterator.next();
        assertFalse(drawLineIterator.hasNext());

        Iterator<DrawPoint> abIterator = AB.stream().iterator();
        assertTrue(abIterator.hasNext());
        DrawPoint A = abIterator.next();
        assertTrue(abIterator.hasNext());
        DrawPoint B = abIterator.next();
        assertFalse(abIterator.hasNext());

        assertEquals(xA, A.getX(), DELTA);
        assertEquals(yA, A.getY(), DELTA);
        assertEquals(xB, B.getX(), DELTA);
        assertEquals(yB, B.getY(), DELTA);

        Iterator<DrawPoint> cdIterator = CD.stream().iterator();
        assertTrue(cdIterator.hasNext());
        DrawPoint C = cdIterator.next();
        assertTrue(cdIterator.hasNext());
        DrawPoint D = cdIterator.next();
        assertFalse(cdIterator.hasNext());

        assertEquals(xC, C.getX(), DELTA);
        assertEquals(yC, C.getY(), DELTA);
        assertEquals(xD, D.getX(), DELTA);
        assertEquals(yD, D.getY(), DELTA);
    }

    private static DrawingPoint buildDrawPoint(float x, float y) {
        return new DrawingPoint(x, y);
    }
}