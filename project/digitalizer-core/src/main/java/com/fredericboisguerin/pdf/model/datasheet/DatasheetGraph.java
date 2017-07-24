package com.fredericboisguerin.pdf.model.datasheet;

import java.util.UUID;

import com.fredericboisguerin.pdf.model.DatasheetGraphExtraInfo;
import com.fredericboisguerin.pdf.model.datasheet.pdf.DatasheetGraphPDF;

public class DatasheetGraph {
    private final UUID uuid = UUID.randomUUID();
    private final DatasheetGraphExtraInfo datasheetGraphExtraInfo;
    private final DatasheetGraphPDF datasheetPdf;

    public DatasheetGraph(DatasheetGraphExtraInfo datasheetGraphExtraInfo, DatasheetGraphPDF datasheetPdf) {
        this.datasheetGraphExtraInfo = datasheetGraphExtraInfo;
        this.datasheetPdf = datasheetPdf;
    }

    public String getId() {
        return uuid.toString();
    }

    public DatasheetGraphPDF getPDFFile() {
        return datasheetPdf;
    }

    public String getFilename() {
        return datasheetPdf.toString();
    }

    public DatasheetGraphExtraInfo getDatasheetGraphExtraInfo() {
        return datasheetGraphExtraInfo;
    }
}
