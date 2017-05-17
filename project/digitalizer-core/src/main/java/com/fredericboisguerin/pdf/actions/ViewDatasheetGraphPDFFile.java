package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetGraph;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class ViewDatasheetGraphPDFFile {
    private final String datasheetId;
    private final String graphId;

    public ViewDatasheetGraphPDFFile(String datasheetId, String graphId) {
        this.datasheetId = datasheetId;
        this.graphId = graphId;
    }

    public DatasheetGraph execute(DatasheetService datasheetService) {
        return datasheetService.getPDFFile(datasheetId, graphId);
    }
}
