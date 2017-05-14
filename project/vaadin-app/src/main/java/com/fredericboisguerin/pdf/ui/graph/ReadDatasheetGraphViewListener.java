package com.fredericboisguerin.pdf.ui.graph;

import java.util.Optional;

public interface ReadDatasheetGraphViewListener {
    void onViewEntered(String parameter);

    void onDatasheetSelectedForExtraction(Optional<DatasheetGraphViewModel> datasheetViewModel);
}
