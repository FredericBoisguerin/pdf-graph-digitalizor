package com.fredericboisguerin.pdf.ui.graph.create;

public interface CreateDatasheetGraphListener {
    void onViewEntered(String datasheetId);

    void onValidateButtonClicked(String filename, byte[] bytes);
}
