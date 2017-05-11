package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetReference;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetSupplier;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class ImportPDF {

    private final PDFFile pdfFile;
    private final DatasheetReference datasheetReference;
    private final DatasheetSupplier supplier;

    public ImportPDF(PDFFile pdfFile, DatasheetReference datasheetReference,
            DatasheetSupplier supplier) {
        this.pdfFile = pdfFile;
        this.datasheetReference = datasheetReference;
        this.supplier = supplier;
    }

    public void execute(DatasheetService datasheetService) {
        datasheetService.importDatasheet(datasheetReference, supplier,  pdfFile);
    }

}
