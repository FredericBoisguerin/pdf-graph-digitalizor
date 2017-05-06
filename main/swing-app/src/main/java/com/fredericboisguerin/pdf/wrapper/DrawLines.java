package com.fredericboisguerin.pdf.wrapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by fred on 10/01/17.
 */
public class DrawLines implements Iterable<DrawLine> {

    private final List<DrawLine> drawLines = new ArrayList<>();

    public void add(DrawLine drawLine) {
        drawLines.add(drawLine);
    }

    public DrawLines getHorizontalGrid() {
        return getFilteredDrawLines(DrawLine::isHorizontal);
    }

    public DrawLines getVerticalGrid() {
        return getFilteredDrawLines(DrawLine::isVertical);
    }

    public DrawLines getSeries() {
        return getFilteredDrawLines(DrawLine::isSeries);
    }

    public DrawLine getMin(Comparator<DrawLine> drawLineComparator) {
        return drawLines.stream().min(drawLineComparator).orElseThrow(IllegalStateException::new);
    }

    public DrawLine getMax(Comparator<DrawLine> drawLineComparator) {
        return drawLines.stream().max(drawLineComparator).orElseThrow(IllegalStateException::new);
    }

    private DrawLines getFilteredDrawLines(Predicate<DrawLine> drawLinePredicate) {
        DrawLines filteredDrawLines = new DrawLines();
        drawLines.stream().filter(drawLinePredicate).forEach(filteredDrawLines::add);
        return filteredDrawLines;
    }

    @Override
    public Iterator<DrawLine> iterator() {
        return drawLines.iterator();
    }
}
