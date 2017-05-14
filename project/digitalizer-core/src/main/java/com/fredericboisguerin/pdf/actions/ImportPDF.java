package com.fredericboisguerin.pdf.actions;

import java.util.UUID;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetReference;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetSupplier;

public class ImportPDF {

    private final DatasheetReference datasheetReference;
    private final DatasheetSupplier supplier;

    public ImportPDF(DatasheetReference datasheetReference, DatasheetSupplier supplier) {
        this.datasheetReference = datasheetReference;
        this.supplier = supplier;
    }

    public UUID execute(DatasheetService datasheetService) {
        return datasheetService.importDatasheet(datasheetReference, supplier);
    }

}
