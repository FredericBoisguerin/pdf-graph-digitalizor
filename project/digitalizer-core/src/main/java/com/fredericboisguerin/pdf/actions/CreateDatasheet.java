package com.fredericboisguerin.pdf.actions;

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

    public void execute(DatasheetService datasheetService) {
        datasheetService.createDatasheet(datasheetReference, supplier);
    }

}
