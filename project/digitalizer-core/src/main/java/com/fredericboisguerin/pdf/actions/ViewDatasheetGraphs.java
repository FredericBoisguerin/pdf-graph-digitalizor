package com.fredericboisguerin.pdf.actions;

import java.util.Collection;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetGraph;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class ViewDatasheetGraphs {

    private final String datasheetId;

    public ViewDatasheetGraphs(String datasheetId) {
        this.datasheetId = datasheetId;
    }

    public Collection<DatasheetGraph> execute(DatasheetService datasheetService) {
        return datasheetService.getAllDatasheetGraphs(datasheetId);
    }
}
