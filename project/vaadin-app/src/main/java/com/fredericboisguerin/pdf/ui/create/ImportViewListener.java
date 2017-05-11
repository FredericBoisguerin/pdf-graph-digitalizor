package com.fredericboisguerin.pdf.ui.create;

public interface ImportViewListener {
    void onValidateButtonClicked(String reference, String supplierName, byte[] lastFileUpdated,
            String filename);
}
