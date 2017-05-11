package com.fredericboisguerin.wrapper;

import com.fredericboisguerin.pdf.drawlines.model.DrawLine;
import com.fredericboisguerin.pdf.drawlines.model.DrawLines;
import com.fredericboisguerin.pdf.drawlines.model.DrawPoint;
import com.fredericboisguerin.pdf.graph.*;
import com.fredericboisguerin.pdf.parser.model.DrawingPoint;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DrawingLinesToXYGraphConverter {

    private final DrawLineHelper drawLineHelper;

    public DrawingLinesToXYGraphConverter(Comparator<Float> coordComparator) {
        drawLineHelper = new DrawLineHelper(coordComparator);
    }

    public XYGraph convert(DrawLines drawLines) {
        DrawLines horizontalGrid = drawLines.getFilteredDrawLines(drawLineHelper::isHorizontal);
        Axis xAxis = getAxis(horizontalGrid, DrawPoint::getX);

        DrawLines verticalGrid = drawLines.getFilteredDrawLines(drawLineHelper::isVertical);
        Axis yAxis = getAxis(verticalGrid, DrawPoint::getY);

        XYGraph graph = new XYGraph(xAxis, yAxis);

        DrawLines series = drawLines.getFilteredDrawLines(drawLineHelper::isSerie);
        series.stream()
              .map(DrawingLinesToXYGraphConverter::buildXYPointSeries)
              .forEach(graph::remove);
        return graph;
    }

    private static XYPointSeries buildXYPointSeries(DrawLine drawLine) {
        XYPointSeries xyPointSeries = new XYPointSeries();
        drawLine.stream()
                .map(DrawingLinesToXYGraphConverter::buildXYPoint)
                .forEach(xyPointSeries::add);
        return xyPointSeries;
    }

    private static XYPoint buildXYPoint(DrawPoint drawingPoint) {
        return new XYPoint(drawingPoint.getX(), drawingPoint.getY());
    }

    private static Axis getAxis(DrawLines drawLines, Function<DrawPoint, Float> coordGetter) {
        Float coordMin = getDrawingPointValue(drawLines, DrawLines::getMin, DrawLine::getMin, coordGetter);
        Float coordMax = getDrawingPointValue(drawLines, DrawLines::getMax, DrawLine::getMax, coordGetter);
        return new LinearAxis(Coord.of(coordMin), Coord.of(coordMax));
    }

    private static Float getDrawingPointValue(DrawLines drawLines,
                                              BiFunction<DrawLines, Comparator<DrawLine>, DrawLine> drawLineExtremumFinder,
                                              BiFunction<DrawLine, Comparator<DrawPoint>, DrawPoint> drawingPointExtremumFinder,
                                              Function<DrawPoint, Float> coordGetter) {
        Comparator<DrawPoint> drawingPointComparator = Comparator
                .comparing(coordGetter, new CoordComparator());
        Comparator<DrawLine> drawLineComparator = Comparator.comparing(o -> drawingPointExtremumFinder
                .apply(o, drawingPointComparator), drawingPointComparator);
        DrawLine extremumLine = drawLineExtremumFinder.apply(drawLines, drawLineComparator);
        DrawPoint extremumPoint = extremumLine.getMin(drawingPointComparator);
        return coordGetter.apply(extremumPoint);
    }
}
