package com.fredericboisguerin.pdf.ui.graph.create;

public interface CreateDatasheetGraphView {
    void setListener(CreateDatasheetGraphListener listener);

    void setDatasheetInfo(String datasheetInfo);

    void notifyMessage(String message);

    void navigateToReadDatasheet(String datasheetId);
}
