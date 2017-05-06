package com.fredericboisguerin.wrapper;

import com.fredericboisguerin.graph.*;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;
import com.fredericboisguerin.pdf.wrapper.DrawLine;
import com.fredericboisguerin.pdf.wrapper.DrawLines;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DrawingLinesToXYGraphConverterImpl implements DrawingLinesToXYGraphConverter {

    @Override
    public EditableXYGraph convert(DrawLines drawLines) {
        DrawLines horizontalGrid = drawLines.getHorizontalGrid();
        Axis xAxis = getAxis(horizontalGrid, DrawingPoint::getX);

        DrawLines verticalGrid = drawLines.getVerticalGrid();
        Axis yAxis = getAxis(verticalGrid, DrawingPoint::getY);

        EditableXYGraph graph = new EditableXYGraph(xAxis, yAxis);

        DrawLines series = drawLines.getSeries();
        for (DrawLine serie : series) {
            XYPointSeries xyPointSeries = buildXYPointSeries(serie);

            graph.add(xyPointSeries);
        }
        return graph;
    }

    private XYPointSeries buildXYPointSeries(DrawLine serie) {
        XYPointSeries xyPointSeries = new XYPointSeries();
        serie.forEach(drawingPoint -> xyPointSeries.add(new XYPoint(drawingPoint.getX(), drawingPoint.getY())));
        return xyPointSeries;
    }

    private Axis getAxis(DrawLines drawLines, Function<DrawingPoint, Float> coordGetter) {
        Float coordMin = getDrawingPointValue(drawLines, DrawLines::getMin, DrawLine::getMin, coordGetter);
        Float coordMax = getDrawingPointValue(drawLines, DrawLines::getMax, DrawLine::getMax, coordGetter);
        return new LinearAxis(Coord.of(coordMin), Coord.of(coordMax));
    }

    private static Float getDrawingPointValue(DrawLines drawLines, BiFunction<DrawLines, Comparator<DrawLine>, DrawLine> comparatorFunction,
                                              BiFunction<DrawLine, Function<DrawingPoint, Float>, DrawingPoint> lineComparator,
                                              Function<DrawingPoint, Float> coordGetter) {
        DrawLine drawLine = comparatorFunction.apply(drawLines, (o1, o2) -> compareDrawLines(o1, o2, lineComparator, coordGetter));
        DrawingPoint drawingPoint = lineComparator.apply(drawLine, coordGetter);
        return coordGetter.apply(drawingPoint);
    }

    private static int compareDrawLines(DrawLine drawLine1, DrawLine drawLine2, BiFunction<DrawLine, Function<DrawingPoint, Float>, DrawingPoint> lineComparator, Function<DrawingPoint, Float> coordGetter) {
        DrawingPoint p1 = lineComparator.apply(drawLine1, coordGetter);
        DrawingPoint p2 = lineComparator.apply(drawLine2, coordGetter);
        return Float.compare(coordGetter.apply(p1), coordGetter.apply(p2));
    }


}
