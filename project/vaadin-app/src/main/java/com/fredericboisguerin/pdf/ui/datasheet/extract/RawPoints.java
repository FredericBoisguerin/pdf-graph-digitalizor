package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class RawPoints implements Iterable<RawPoint>{
    private final List<RawPoint> rawPoints = new ArrayList<>();

    public RawPoints(List<RawPoint> rawPointList) {
        rawPoints.addAll(rawPointList);
    }

    public int size() {
        return rawPoints.size();
    }

    @Override
    public Iterator<RawPoint> iterator() {
        return rawPoints.iterator();
    }
}
