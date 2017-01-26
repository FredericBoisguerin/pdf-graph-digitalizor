package com.fredericboisguerin.graph.model.input;

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

    @Override
    public Iterator<DrawLine> iterator() {
        return drawLines.iterator();
    }
}
