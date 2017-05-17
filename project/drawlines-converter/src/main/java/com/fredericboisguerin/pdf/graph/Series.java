package com.fredericboisguerin.pdf.graph;

import java.util.*;
import java.util.function.Function;

public class Series {
    private final List<Serie> serieList = new ArrayList<>();
    private final Set<UUID> selected = new HashSet<>();

    public void add(Serie serie) {
        serieList.add(serie);
    }

    public void addTransposedSeriesTo(Series series, Function<Coord, Coord> xConverter,
                                      Function<Coord, Coord> yConverter) {
        serieList.forEach(value -> {
            Serie transposedSerie = value.convert(xConverter, yConverter);
            series.serieList.add(transposedSerie);
        });
        series.selected.addAll(selected);
    }

    public void addOnlySelectedTo(Series series) {
        serieList.stream()
                 .filter(this::isSelected)
                 .forEach(series::addAndSelect);
    }

    private boolean isSelected(Serie serie) {
        UUID uuid = serie.getUuid();
        return selected.contains(uuid);
    }

    private void addAndSelect(Serie serie) {
        add(serie);
        selected.add(serie.getUuid());
    }

    public int size() {
        return serieList.size();
    }

    public List<Serie> getSeriesBySizeDesc() {
        Comparator<Serie> comparator = Comparator.comparing(Serie::size)
                                                 .reversed();
        ArrayList<Serie> sorted = new ArrayList<>(serieList);
        sorted.sort(comparator);
        return Collections.unmodifiableList(sorted);
    }

    public void selectOnly(Collection<UUID> selectedElements) {
        selected.clear();
        selected.addAll(selectedElements);
    }
}
