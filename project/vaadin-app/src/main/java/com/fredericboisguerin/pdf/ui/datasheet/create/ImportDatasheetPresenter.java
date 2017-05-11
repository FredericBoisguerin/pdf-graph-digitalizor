package com.fredericboisguerin.pdf.ui.datasheet.create;

import com.fredericboisguerin.pdf.actions.ImportPDF;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetReference;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetSupplier;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

public class ImportDatasheetPresenter implements ImportDatasheetViewListener {

    private final DatasheetService datasheetService;
    private final ImportDatasheetView importDatasheetView;

    public ImportDatasheetPresenter(ImportDatasheetView importDatasheetView, DatasheetService datasheetService) {
        this.importDatasheetView = importDatasheetView;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onValidateButtonClicked(String reference, String supplierName, byte[] file,
            String filename) {
        ImportPDF importPDF = new ImportPDF(new PDFFile(filename, file), new DatasheetReference(reference),
                new DatasheetSupplier(supplierName));
        importPDF.execute(datasheetService);

        String message = String.format("Datasheet %s (%s) imported!", reference, supplierName);
        importDatasheetView.notifyMessage(message);
        importDatasheetView.navigateToRoot();
    }
}
