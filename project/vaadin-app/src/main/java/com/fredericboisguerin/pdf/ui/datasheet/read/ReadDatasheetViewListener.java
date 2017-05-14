package com.fredericboisguerin.pdf.ui.datasheet.read;

import java.util.Optional;

public interface ReadDatasheetViewListener {
    void onViewEntered();

    void onDatasheetSelectedForViewGraphs(Optional<DatasheetViewModel> datasheetViewModel);
}
