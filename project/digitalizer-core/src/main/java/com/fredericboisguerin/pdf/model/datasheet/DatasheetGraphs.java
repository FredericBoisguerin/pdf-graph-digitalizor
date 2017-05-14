package com.fredericboisguerin.pdf.model.datasheet;

import java.util.ArrayList;
import java.util.List;

class DatasheetGraphs {

    private final List<DatasheetGraph> datasheetGraphs = new ArrayList<>();

    public void add(DatasheetGraph datasheetGraph) {
        datasheetGraphs.add(datasheetGraph);
    }

    public PDFFile getFirst() {
        return datasheetGraphs.get(0).getPDFFile();
    }
}
