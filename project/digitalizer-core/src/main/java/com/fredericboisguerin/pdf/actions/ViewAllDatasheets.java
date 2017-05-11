package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetCollection;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class ViewAllDatasheets {


    public DatasheetCollection execute(DatasheetService datasheetService){
        return new DatasheetCollection(datasheetService.getAllDatasheets());
    }
}
