package com.fredericboisguerin.pdf.model.datasheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class DatasheetGraphs {

    private final List<DatasheetGraph> datasheetGraphs = new ArrayList<>();

    void add(DatasheetGraph datasheetGraph) {
        datasheetGraphs.add(datasheetGraph);
    }

    public Collection<DatasheetGraph> getList() {
        return datasheetGraphs.stream().collect(Collectors.toList());
    }

    public Optional<DatasheetGraph> get(String graphId) {
        return datasheetGraphs.stream()
                              .filter(datasheetGraph -> graphId.equals(datasheetGraph.getId()))
                              .findFirst();
    }

    public void remove(String graphId) {
        datasheetGraphs.removeIf(datasheetGraph -> datasheetGraph.getId().equals(graphId));
    }
}
