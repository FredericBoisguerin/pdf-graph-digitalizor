package com.fredericboisguerin.pdf.ui.datasheet.edit;

public interface EditDatasheetViewListener {
    void onViewEntered(String parameters);

    void onFormValidated(String reference, String supplier);
}
