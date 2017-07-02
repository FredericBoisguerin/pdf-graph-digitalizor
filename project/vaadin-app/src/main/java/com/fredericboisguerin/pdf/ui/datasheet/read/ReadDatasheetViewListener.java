package com.fredericboisguerin.pdf.ui.datasheet.read;

public interface ReadDatasheetViewListener {
    void onViewEntered();

    void onDatasheetSelectedForViewGraphs(DatasheetViewModel datasheetViewModel);

    void onDatasheetSelectedForAddGraph(DatasheetViewModel datasheetViewModel);

    void onCreateDatasheetButtonClicked();

    void onDatasheetSelectedForEditNameAndSuppplier(DatasheetViewModel datasheetViewModel);
}
