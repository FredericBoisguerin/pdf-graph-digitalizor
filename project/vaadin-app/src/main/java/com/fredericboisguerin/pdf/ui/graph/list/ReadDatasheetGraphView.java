package com.fredericboisguerin.pdf.ui.graph.list;

import java.util.List;

public interface ReadDatasheetGraphView {
    void setListener(ReadDatasheetGraphViewListener readDatasheetPresenter);

    void setDatasheetInfo(String datasheetInfo);

    void setDatasheets(List<DatasheetGraphViewModel> datasheetGraphViewModelList);

    void navigateToExtractDatasheetData(String datasheetId, String graphId);

    void navigateToNewGraph(String params);
}
