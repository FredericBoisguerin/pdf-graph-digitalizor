package com.fredericboisguerin.pdf.ui.graph.list;

import java.util.Optional;

public interface ReadDatasheetGraphViewListener {
    void onViewEntered(String parameter);

    void onDatasheetSelectedForExtraction(Optional<DatasheetGraphViewModel> datasheetViewModel);
}
