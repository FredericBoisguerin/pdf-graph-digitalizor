package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fredericboisguerin.pdf.graph.Coord;
import com.fredericboisguerin.pdf.graph.PointCoords;
import com.fredericboisguerin.pdf.graph.Serie;

class RawPointsBuilder {

    public RawPoints buildRawPoints(Serie serie) {
        List<RawPoint> rawPointList = StreamSupport.stream(serie.spliterator(), false)
                                                   .map(RawPointsBuilder::buildRawPoint)
                                                   .collect(Collectors.toList());
        return new RawPoints(rawPointList);
    }

    private static RawPoint buildRawPoint(PointCoords pointCoords) {
        float x = buildRawCoord(pointCoords.getX());
        float y = buildRawCoord(pointCoords.getY());
        return new RawPoint(x, y);
    }

    private static float buildRawCoord(Coord coord) {
        return coord.getCoord();
    }
}
