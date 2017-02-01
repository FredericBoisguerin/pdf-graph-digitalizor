package com.fredericboisguerin.pdf.wrapper;

import com.fredericboisguerin.pdf.parser.model.DrawingAction;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;
import com.fredericboisguerin.pdf.parser.model.LineTo;
import com.fredericboisguerin.pdf.parser.model.MoveTo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fred on 11/01/17.
 */
public class DrawingActionsToDrawLinesConverterImplTest {

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
        converter = new DrawingActionsToDrawLinesConverterImpl();
    }

    @Test
    public void should_have_one_series_with_three_points_after_1_moveTo_and_2_lineTo() throws Exception {
        List<DrawingAction> drawingActionList = new ArrayList<>();
        drawingActionList.add(new MoveTo(new DrawingPoint(xA, yA)));
        drawingActionList.add(new LineTo(new DrawingPoint(xB, yB)));
        drawingActionList.add(new LineTo(new DrawingPoint(xC, yC)));

        DrawLines convert = converter.convert(drawingActionList);

        DrawLines series = convert.getSeries();
        Iterator<DrawLine> drawLineIterator = series.iterator();

        assertTrue(drawLineIterator.hasNext());
        DrawLine AC = drawLineIterator.next();
        assertFalse(drawLineIterator.hasNext());

        Iterator<DrawingPoint> acIterator = AC.iterator();
        assertTrue(acIterator.hasNext());
        DrawingPoint A = acIterator.next();
        assertTrue(acIterator.hasNext());
        DrawingPoint B = acIterator.next();
        assertTrue(acIterator.hasNext());
        DrawingPoint C = acIterator.next();
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
        drawingActionList.add(new MoveTo(new DrawingPoint(xA, yA)));
        drawingActionList.add(new LineTo(new DrawingPoint(xB, yB)));
        drawingActionList.add(new MoveTo(new DrawingPoint(xB, yB)));
        drawingActionList.add(new LineTo(new DrawingPoint(xC, yC)));

        DrawLines convert = converter.convert(drawingActionList);

        DrawLines series = convert.getSeries();
        Iterator<DrawLine> drawLineIterator = series.iterator();

        assertTrue(drawLineIterator.hasNext());
        DrawLine AC = drawLineIterator.next();
        assertFalse(drawLineIterator.hasNext());

        Iterator<DrawingPoint> acIterator = AC.iterator();
        assertTrue(acIterator.hasNext());
        DrawingPoint A = acIterator.next();
        assertTrue(acIterator.hasNext());
        DrawingPoint B = acIterator.next();
        assertTrue(acIterator.hasNext());
        DrawingPoint C = acIterator.next();
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
        drawingActionList.add(new MoveTo(new DrawingPoint(xA, yA)));
        drawingActionList.add(new LineTo(new DrawingPoint(xB, yB)));
        drawingActionList.add(new MoveTo(new DrawingPoint(xC, yC)));
        drawingActionList.add(new LineTo(new DrawingPoint(xD, yD)));

        DrawLines convert = converter.convert(drawingActionList);

        DrawLines series = convert.getSeries();
        Iterator<DrawLine> drawLineIterator = series.iterator();

        assertTrue(drawLineIterator.hasNext());
        DrawLine AB = drawLineIterator.next();
        assertTrue(drawLineIterator.hasNext());
        DrawLine CD = drawLineIterator.next();
        assertFalse(drawLineIterator.hasNext());

        Iterator<DrawingPoint> abIterator = AB.iterator();
        assertTrue(abIterator.hasNext());
        DrawingPoint A = abIterator.next();
        assertTrue(abIterator.hasNext());
        DrawingPoint B = abIterator.next();
        assertFalse(abIterator.hasNext());

        assertEquals(xA, A.getX(), DELTA);
        assertEquals(yA, A.getY(), DELTA);
        assertEquals(xB, B.getX(), DELTA);
        assertEquals(yB, B.getY(), DELTA);

        Iterator<DrawingPoint> cdIterator = CD.iterator();
        assertTrue(cdIterator.hasNext());
        DrawingPoint C = cdIterator.next();
        assertTrue(cdIterator.hasNext());
        DrawingPoint D = cdIterator.next();
        assertFalse(cdIterator.hasNext());

        assertEquals(xC, C.getX(), DELTA);
        assertEquals(yC, C.getY(), DELTA);
        assertEquals(xD, D.getX(), DELTA);
        assertEquals(yD, D.getY(), DELTA);
    }
}