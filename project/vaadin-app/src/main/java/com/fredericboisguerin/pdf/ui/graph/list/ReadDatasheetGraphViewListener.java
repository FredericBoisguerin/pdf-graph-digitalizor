package com.fredericboisguerin.pdf.ui.graph.list;

public interface ReadDatasheetGraphViewListener {
    void onViewEntered(String parameter);

    void onDatasheetSelectedForExtraction(DatasheetGraphViewModel datasheetViewModel);

    void onGraphSelectedForRemoval(DatasheetGraphViewModel datasheetGraphViewModel);

    void onNewGraph();
}
