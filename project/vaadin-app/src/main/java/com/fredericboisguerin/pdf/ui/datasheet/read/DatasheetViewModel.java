package com.fredericboisguerin.pdf.ui.datasheet.read;

class DatasheetViewModel {
    private final String reference;
    private final String supplier;

    public DatasheetViewModel(String reference, String supplier) {
        this.reference = reference;
        this.supplier = supplier;
    }

    public String getReference() {
        return reference;
    }

    public String getSupplier() {
        return supplier;
    }
}
