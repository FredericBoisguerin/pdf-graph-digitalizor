package com.fredericboisguerin.pdf.actions;

import java.util.UUID;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetReference;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetSupplier;

public class CreateDatasheet {

    private final DatasheetReference datasheetReference;
    private final DatasheetSupplier supplier;

    public CreateDatasheet(DatasheetReference datasheetReference, DatasheetSupplier supplier) {
        this.datasheetReference = datasheetReference;
        this.supplier = supplier;
    }

    public UUID execute(DatasheetService datasheetService) {
        return datasheetService.createDatasheet(datasheetReference, supplier);
    }

}
