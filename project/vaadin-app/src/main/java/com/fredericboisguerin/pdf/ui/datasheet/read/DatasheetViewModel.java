package com.fredericboisguerin.pdf.ui.datasheet.read;

class DatasheetViewModel {
    private final String id;
    private final String reference;
    private final String supplier;

    public DatasheetViewModel(String id, String reference, String supplier) {
        this.id = id;
        this.reference = reference;
        this.supplier = supplier;
    }

    public String getReference() {
        return reference;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getId() {
        return id;
    }
}
