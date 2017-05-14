package com.fredericboisguerin.pdf.ui.graph.list;

class DatasheetGraphViewModel {
    private final String id;
    private final String filename;

    public DatasheetGraphViewModel(String id, String filename) {
        this.id = id;
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public String getId() {
        return id;
    }
}
