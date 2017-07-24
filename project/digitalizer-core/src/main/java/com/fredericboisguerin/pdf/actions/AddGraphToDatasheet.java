package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.DatasheetGraphExtraInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetGraph;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.pdf.DatasheetGraphPDF;

public class AddGraphToDatasheet {

    private final String datasheetId;
    private final DatasheetGraphExtraInfo datasheetGraphExtraInfo;
    private final DatasheetGraphPDF datasheetPdf;

    public AddGraphToDatasheet(String datasheetId, DatasheetGraphExtraInfo datasheetGraphExtraInfo,
                               DatasheetGraphPDF datasheetPdf) {
        this.datasheetId = datasheetId;
        this.datasheetGraphExtraInfo = datasheetGraphExtraInfo;
        this.datasheetPdf = datasheetPdf;
    }

    public void execute(DatasheetService datasheetService) {
        datasheetService.addGraphFromPDF(datasheetId, new DatasheetGraph(datasheetGraphExtraInfo, datasheetPdf));
    }
}
