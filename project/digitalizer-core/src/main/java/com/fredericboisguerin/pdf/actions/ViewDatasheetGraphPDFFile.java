package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

public class ViewDatasheetGraphPDFFile {
    private final String datasheetId;
    private final String graphId;

    public ViewDatasheetGraphPDFFile(String datasheetId, String graphId) {
        this.datasheetId = datasheetId;
        this.graphId = graphId;
    }

    public PDFFile execute(DatasheetService datasheetService) {
        return datasheetService.getPDFFile(datasheetId, graphId);
    }
}
