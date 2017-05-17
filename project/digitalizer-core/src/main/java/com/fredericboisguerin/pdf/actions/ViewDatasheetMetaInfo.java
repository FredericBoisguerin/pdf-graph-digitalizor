package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class ViewDatasheetMetaInfo {

    private final String datasheetId;

    public ViewDatasheetMetaInfo(String datasheetId) {
        this.datasheetId = datasheetId;
    }

    public DatasheetMetaInfo execute(DatasheetService datasheetService) {
        return datasheetService.getDatasheetMetaInfo(datasheetId);
    }
}
