package com.fredericboisguerin.pdf.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class Serie implements Iterable<PointCoords> {

    private final UUID uuid;
    private final List<PointCoords> pointCoordsCoords = new ArrayList<>();

    public Serie() {
        this(UUID.randomUUID());
    }

    private Serie(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void add(PointCoords pointCoords) {
        this.pointCoordsCoords.add(pointCoords);
    }

    public Serie convert(Function<Coord, Coord> xTransformation, Function<Coord, Coord> yTransformation) {
        Serie series = new Serie(this.uuid);
        pointCoordsCoords.stream()
                         .map(pointCoords -> new PointCoords(xTransformation.apply(pointCoords
                                 .getX()), yTransformation.apply(
                                 pointCoords.getY())))
                         .forEach(series::add);
        return series;
    }

    @Override
    public String toString() {
        return String.format("Série #%d (%d points)", this.hashCode(), pointCoordsCoords
                .size());
    }

    @Override
    public Iterator<PointCoords> iterator() {
        return pointCoordsCoords.iterator();
    }

    public int size() {
        return pointCoordsCoords.size();
    }
}
