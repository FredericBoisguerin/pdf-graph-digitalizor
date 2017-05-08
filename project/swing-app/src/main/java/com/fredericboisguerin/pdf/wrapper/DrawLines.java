package com.fredericboisguerin.pdf.wrapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DrawLines {

    private final List<DrawLine> drawLines = new ArrayList<>();

    public void add(DrawLine drawLine) {
        drawLines.add(drawLine);
    }

    public DrawLine getMin(Comparator<DrawLine> drawLineComparator) {
        return drawLines.stream()
                        .min(drawLineComparator)
                        .orElseThrow(IllegalStateException::new);
    }

    public DrawLine getMax(Comparator<DrawLine> drawLineComparator) {
        return drawLines.stream()
                        .max(drawLineComparator)
                        .orElseThrow(IllegalStateException::new);
    }

    public DrawLines getFilteredDrawLines(Predicate<DrawLine> drawLinePredicate) {
        DrawLines filteredDrawLines = new DrawLines();
        drawLines.stream()
                 .filter(drawLinePredicate)
                 .forEach(filteredDrawLines::add);
        return filteredDrawLines;
    }

    public Stream<DrawLine> stream() {
        return drawLines.stream();
    }
}
