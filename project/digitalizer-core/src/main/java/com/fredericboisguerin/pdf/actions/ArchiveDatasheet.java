package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class ArchiveDatasheet {

    private final String datasheetId;

    public ArchiveDatasheet(String datasheetId) {
        this.datasheetId = datasheetId;
    }

    public void execute(DatasheetService datasheetService){
        datasheetService.archive(datasheetId);
    }
}
