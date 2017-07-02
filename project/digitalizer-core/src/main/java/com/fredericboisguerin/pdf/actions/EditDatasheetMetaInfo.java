package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class EditDatasheetMetaInfo {

    private final String datasheetId;
    private final DatasheetMetaInfo datasheetMetaInfo;


    public EditDatasheetMetaInfo(String datasheetId, DatasheetMetaInfo datasheetMetaInfo) {
        this.datasheetId = datasheetId;
        this.datasheetMetaInfo = datasheetMetaInfo;
    }

    public void execute(DatasheetService datasheetService){
        datasheetService.setDatasheetMetaInfo(datasheetId, datasheetMetaInfo);
    }
}
