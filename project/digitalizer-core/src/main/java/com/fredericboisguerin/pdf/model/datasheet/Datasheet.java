package com.fredericboisguerin.pdf.model.datasheet;

import java.util.UUID;

public class Datasheet {
    private final UUID uuid = UUID.randomUUID();
    private final DatasheetReference datasheetReference;
    private final DatasheetSupplier supplier;
    private final DatasheetGraphs datasheetGraphs = new DatasheetGraphs();

    public Datasheet(DatasheetReference datasheetReference, DatasheetSupplier supplier) {
        this.datasheetReference = datasheetReference;
        this.supplier = supplier;
    }

    public DatasheetReference getDatasheetReference() {
        return datasheetReference;
    }

    public DatasheetSupplier getSupplier() {
        return supplier;
    }

    public boolean idEquals(String parameter) {
        UUID other = UUID.fromString(parameter);
        return this.uuid.equals(other);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", datasheetReference, supplier);
    }

    public PDFFile getPDFFile() {
        return datasheetGraphs.getFirst();
    }

    public String getId() {
        return uuid.toString();
    }

    public void addGraph(DatasheetGraph datasheetGraph) {
        datasheetGraphs.add(datasheetGraph);
    }

    public UUID getUUID() {
        return uuid;
    }
}
