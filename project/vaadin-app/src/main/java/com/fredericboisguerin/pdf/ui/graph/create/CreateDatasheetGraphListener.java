package com.fredericboisguerin.pdf.ui.graph.create;

public interface CreateDatasheetGraphListener extends PDFDocumentEditorListener, FileUpdaterListener, PageSelectionListener {
    void onViewEntered(String datasheetId);

    void onValidateButtonClicked();
}
