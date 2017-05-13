package com.fredericboisguerin.pdf.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

public class Series {
    private final Map<UUID, Serie> serieMap = new HashMap<>();
    private final Set<UUID> selected = new HashSet<>();

    public void add(Serie serie) {
        UUID uuid = UUID.randomUUID();
        serieMap.put(uuid, serie);
    }

    public void addTransposedSeriesTo(Series series, Function<Coord, Coord> xConverter,
            Function<Coord, Coord> yConverter) {
        serieMap.forEach((key, value) -> {
            Serie transposedSerie = value.convert(xConverter, yConverter);
            series.serieMap.put(key, transposedSerie);
        });
        series.selected.addAll(selected);
    }

    public void select(Serie serie) {
        serieMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == serie)
                .map(Entry::getKey)
                .forEach(selected::add);
    }

    public void addOnlySelectedTo(Series series) {
        selected.stream().map(serieMap::get).forEach(series::addAndSelect);
    }

    private void addAndSelect(Serie serie) {
        add(serie);
        select(serie);
    }

    public int size() {
        return serieMap.size();
    }

    public List<Serie> getSeriesBySizeDesc() {
        Comparator<Serie> comparator = Comparator.comparing(Serie::size).reversed();
        ArrayList<Serie> sorted = new ArrayList<>(serieMap.values());
        sorted.sort(comparator);
        return Collections.unmodifiableList(sorted);
    }
}
