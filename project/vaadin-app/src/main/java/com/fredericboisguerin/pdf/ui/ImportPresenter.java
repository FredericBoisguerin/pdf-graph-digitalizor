package com.fredericboisguerin.pdf.ui;

import com.fredericboisguerin.pdf.actions.ImportPDF;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetReference;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetSupplier;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

public class ImportPresenter implements ImportViewListener {

    private final DatasheetService datasheetService;

    public ImportPresenter(DatasheetService datasheetService) {
        this.datasheetService = datasheetService;
    }

    @Override
    public void onValidateButtonClicked(String reference, String supplierName, byte[] file,
            String filename) {
        ImportPDF importPDF = new ImportPDF(new PDFFile(filename, file), new DatasheetReference(reference),
                new DatasheetSupplier(supplierName));
        importPDF.execute(datasheetService);
    }
}
