package com.fredericboisguerin.pdf.ui.create;

import com.fredericboisguerin.pdf.actions.ImportPDF;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetReference;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetSupplier;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

public class ImportPresenter implements ImportViewListener {

    private final DatasheetService datasheetService;
    private final ImportView importView;

    public ImportPresenter(ImportView importView,DatasheetService datasheetService) {
        this.importView = importView;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onValidateButtonClicked(String reference, String supplierName, byte[] file,
            String filename) {
        ImportPDF importPDF = new ImportPDF(new PDFFile(filename, file), new DatasheetReference(reference),
                new DatasheetSupplier(supplierName));
        importPDF.execute(datasheetService);

        String message = String.format("Datasheet %s (%s) imported!", reference, supplierName);
        importView.notifyMessage(message);
        importView.navigateTo("");
    }
}
