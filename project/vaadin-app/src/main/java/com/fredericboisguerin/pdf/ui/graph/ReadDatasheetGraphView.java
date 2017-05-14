package com.fredericboisguerin.pdf.ui.graph;

import java.util.List;

public interface ReadDatasheetGraphView {
    void setListener(ReadDatasheetGraphViewListener readDatasheetPresenter);

    void setDatasheetInfo(String datasheetInfo);

    void setDatasheets(List<DatasheetGraphViewModel> datasheetGraphViewModelList);

    void notifyTray(String message);

    void navigateToExtractDatasheetData(String datasheetId, String graphId);
}
