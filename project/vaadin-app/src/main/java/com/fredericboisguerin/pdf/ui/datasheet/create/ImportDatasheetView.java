package com.fredericboisguerin.pdf.ui.datasheet.create;

public interface ImportDatasheetView {

    void setListener(ImportDatasheetViewListener listener);

    void notifyMessage(String message);

    void navigateToRoot();
}
