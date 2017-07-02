package com.fredericboisguerin.pdf.ui.datasheet.edit;

import com.fredericboisguerin.pdf.actions.EditDatasheetMetaInfo;
import com.fredericboisguerin.pdf.actions.ViewDatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetReference;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetSupplier;

public class EditDatasheetPresenter implements EditDatasheetViewListener {

    private final DatasheetService datasheetService;
    private final EditDatasheetView view;
    private String datasheetId;

    public EditDatasheetPresenter(DatasheetService datasheetService, EditDatasheetView view) {
        this.datasheetService = datasheetService;
        this.view = view;
    }

    @Override
    public void onViewEntered(String parameters) {
        this.datasheetId = parameters;
        ViewDatasheetMetaInfo viewDatasheetMetaInfo = new ViewDatasheetMetaInfo(datasheetId);
        DatasheetMetaInfo datasheetMetaInfo = viewDatasheetMetaInfo.execute(datasheetService);
        DatasheetReference reference = datasheetMetaInfo.getReference();
        DatasheetSupplier supplier = datasheetMetaInfo.getSupplier();
        view.setDatasheetInfo(reference.toString(), supplier.toString());
    }

    @Override
    public void onFormValidated(String reference, String supplier) {
        DatasheetReference datasheetReference = new DatasheetReference(reference);
        DatasheetSupplier datasheetSupplier = new DatasheetSupplier(supplier);
        EditDatasheetMetaInfo editDatasheetMetaInfo = new EditDatasheetMetaInfo(datasheetId, new DatasheetMetaInfo(datasheetReference, datasheetSupplier));
        editDatasheetMetaInfo.execute(datasheetService);
        view.navigateToReadDatasheetView();
    }
}
