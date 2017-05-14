package com.fredericboisguerin.pdf.ui.datasheet.create;

public interface CreateDatasheetViewListener {
    void onValidateButtonClicked(String reference, String supplierName, byte[] lastFileUpdated,
            String filename);
}
