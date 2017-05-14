package com.fredericboisguerin.pdf.ui.datasheet.create;

import java.util.UUID;

import com.fredericboisguerin.pdf.actions.AddGraphToDatasheet;
import com.fredericboisguerin.pdf.actions.CreateDatasheet;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetReference;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetSupplier;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

public class CreateDatasheetPresenter implements CreateDatasheetViewListener {

    private final DatasheetService datasheetService;
    private final CreateDatasheetView createDatasheetView;

    public CreateDatasheetPresenter(CreateDatasheetView createDatasheetView, DatasheetService datasheetService) {
        this.createDatasheetView = createDatasheetView;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onValidateButtonClicked(String reference, String supplierName, byte[] file,
            String filename) {
        CreateDatasheet createDatasheet = new CreateDatasheet(new DatasheetReference(reference),
                new DatasheetSupplier(supplierName));
        UUID datasheetId = createDatasheet.execute(datasheetService);

        PDFFile pdfFile = new PDFFile(filename, file);
        AddGraphToDatasheet addGraphToDatasheet = new AddGraphToDatasheet(datasheetId, pdfFile);
        addGraphToDatasheet.execute(datasheetService);

        String message = String.format("Datasheet %s (%s) imported!", reference, supplierName);
        createDatasheetView.notifyMessage(message);
        createDatasheetView.navigateToRoot();
    }
}
