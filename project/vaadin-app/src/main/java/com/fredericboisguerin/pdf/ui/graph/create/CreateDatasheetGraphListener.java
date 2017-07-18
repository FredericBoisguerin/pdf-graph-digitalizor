package com.fredericboisguerin.pdf.ui.graph.create;

public interface CreateDatasheetGraphListener extends PDFDocumentEditorListener {
    void onViewEntered(String datasheetId);

    void onValidateButtonClicked();
}
