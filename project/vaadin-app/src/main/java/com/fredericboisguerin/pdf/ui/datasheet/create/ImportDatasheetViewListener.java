package com.fredericboisguerin.pdf.ui.datasheet.create;

public interface ImportDatasheetViewListener {
    void onValidateButtonClicked(String reference, String supplierName, byte[] lastFileUpdated,
            String filename);
}
