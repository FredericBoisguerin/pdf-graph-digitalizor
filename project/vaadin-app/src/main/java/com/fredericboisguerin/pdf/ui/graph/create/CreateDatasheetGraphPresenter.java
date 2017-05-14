package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.actions.AddGraphToDatasheet;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

public class CreateDatasheetGraphPresenter implements CreateDatasheetGraphListener {
    private final CreateDatasheetGraphView view;
    private final DatasheetService datasheetService;

    private String datasheetId;

    public CreateDatasheetGraphPresenter(CreateDatasheetGraphView view,
            DatasheetService datasheetService) {
        this.view = view;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onViewEntered(String datasheetId) {
        this.datasheetId = datasheetId;
        String datasheetInfo = datasheetService.getDatasheetInfo(datasheetId);
        view.setDatasheetInfo(datasheetInfo);
    }

    @Override
    public void onValidateButtonClicked(String filename, byte[] bytes) {
        PDFFile pdfFile = new PDFFile(filename, bytes);
        AddGraphToDatasheet addGraphToDatasheet = new AddGraphToDatasheet(datasheetId, pdfFile);
        addGraphToDatasheet.execute(datasheetService);
        view.notifyMessage("Graph created!");
        view.navigateToReadDatasheet(datasheetId);
    }
}
