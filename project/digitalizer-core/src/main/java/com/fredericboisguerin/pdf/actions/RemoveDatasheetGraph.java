package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class RemoveDatasheetGraph {

    private final String datasheetId;
    private final String graphId;


    public RemoveDatasheetGraph(String datasheetId, String graphId) {
        this.datasheetId = datasheetId;
        this.graphId = graphId;
    }

    public void execute(DatasheetService datasheetService) {
        datasheetService.removeGraph(datasheetId, graphId);
    }
}
