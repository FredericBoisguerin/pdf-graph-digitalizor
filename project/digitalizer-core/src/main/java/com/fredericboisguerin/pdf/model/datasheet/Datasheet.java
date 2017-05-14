package com.fredericboisguerin.pdf.model.datasheet;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class Datasheet {
    private final UUID uuid = UUID.randomUUID();
    private final DatasheetReference datasheetReference;
    private final DatasheetSupplier supplier;
    private final DatasheetGraphs datasheetGraphs = new DatasheetGraphs();

    Datasheet(DatasheetReference datasheetReference, DatasheetSupplier supplier) {
        this.datasheetReference = datasheetReference;
        this.supplier = supplier;
    }

    public DatasheetReference getDatasheetReference() {
        return datasheetReference;
    }

    public DatasheetSupplier getSupplier() {
        return supplier;
    }

    public Collection<DatasheetGraph> getDatasheetGraphs() {
        return datasheetGraphs.getList();
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", datasheetReference, supplier);
    }

    public String getId() {
        return uuid.toString();
    }

    void addGraph(DatasheetGraph datasheetGraph) {
        datasheetGraphs.add(datasheetGraph);
    }

    UUID getUUID() {
        return uuid;
    }

    public Optional<DatasheetGraph> getDatasheetGraphs(String graphId) {
        return datasheetGraphs.get(graphId);
    }
}
