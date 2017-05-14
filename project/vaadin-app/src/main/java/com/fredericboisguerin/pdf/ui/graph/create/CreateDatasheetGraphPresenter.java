package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class CreateDatasheetGraphPresenter implements CreateDatasheetGraphListener {
    private final VaadinCreateDatasheetGraphView view;
    private final DatasheetService datasheetService;

    public CreateDatasheetGraphPresenter(VaadinCreateDatasheetGraphView view,
            DatasheetService datasheetService) {
        this.view = view;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onViewEntered(String datasheetId) {
        String datasheetInfo = datasheetService.getDatasheetInfo(datasheetId);
        view.setDatasheetInfo(datasheetInfo);
    }
}
