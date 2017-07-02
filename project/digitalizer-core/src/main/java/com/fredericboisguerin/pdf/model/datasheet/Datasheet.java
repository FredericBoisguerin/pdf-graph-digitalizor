package com.fredericboisguerin.pdf.model.datasheet;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class Datasheet {
    private final UUID uuid = UUID.randomUUID();
    private final DatasheetGraphs datasheetGraphs = new DatasheetGraphs();
    private DatasheetMetaInfo datasheetMetaInfo;
    private boolean archived = false;

    Datasheet(DatasheetMetaInfo datasheetMetaInfo) {
        this.datasheetMetaInfo = datasheetMetaInfo;
    }

    public DatasheetMetaInfo getDatasheetMetaInfo() {
        return datasheetMetaInfo;
    }

    public Collection<DatasheetGraph> getDatasheetGraphs() {
        return datasheetGraphs.getList();
    }

    @Override
    public String toString() {
        return String.format("%s", datasheetMetaInfo);
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

    Optional<DatasheetGraph> getDatasheetGraphs(String graphId) {
        return datasheetGraphs.get(graphId);
    }

    void setMetadata(DatasheetMetaInfo datasheetMetaInfo) {
        this.datasheetMetaInfo = datasheetMetaInfo;
    }

    boolean isArchived() {
        return archived;
    }

    void archive() {
        this.archived = true;
    }
}
