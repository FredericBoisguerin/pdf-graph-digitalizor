package com.fredericboisguerin.pdf.ui.datasheet.create;

import com.fredericboisguerin.pdf.actions.CreateDatasheet;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetReference;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetSupplier;

public class CreateDatasheetPresenter implements CreateDatasheetViewListener {

    private final DatasheetService datasheetService;
    private final CreateDatasheetView createDatasheetView;

    public CreateDatasheetPresenter(CreateDatasheetView createDatasheetView, DatasheetService datasheetService) {
        this.createDatasheetView = createDatasheetView;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onValidateButtonClicked(String reference, String supplierName) {
        CreateDatasheet createDatasheet = new CreateDatasheet(new DatasheetReference(reference),
                new DatasheetSupplier(supplierName));
        createDatasheet.execute(datasheetService);

        String message = String.format("Datasheet %s (%s) created!", reference, supplierName);
        createDatasheetView.notifyMessage(message);
        createDatasheetView.navigateToRoot();
    }
}
