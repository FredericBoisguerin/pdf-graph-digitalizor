package com.fredericboisguerin.pdf.ui.datasheet.create;

public interface CreateDatasheetView {

    void setListener(CreateDatasheetViewListener listener);

    void notifyMessage(String message);

    void navigateToRoot();
}
