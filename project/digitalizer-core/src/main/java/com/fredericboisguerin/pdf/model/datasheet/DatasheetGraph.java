package com.fredericboisguerin.pdf.model.datasheet;

import java.util.UUID;

import com.fredericboisguerin.pdf.model.DatasheetGraphExtraInfo;

public class DatasheetGraph {
    private final UUID uuid = UUID.randomUUID();
    private final DatasheetGraphExtraInfo datasheetGraphExtraInfo;
    private final PDFFile pdfFile;

    public DatasheetGraph(DatasheetGraphExtraInfo datasheetGraphExtraInfo, PDFFile pdfFile) {
        this.datasheetGraphExtraInfo = datasheetGraphExtraInfo;
        this.pdfFile = pdfFile;
    }

    public String getId() {
        return uuid.toString();
    }

    public PDFFile getPDFFile() {
        return pdfFile;
    }

    public String getFilename() {
        return pdfFile.toString();
    }

    public DatasheetGraphExtraInfo getDatasheetGraphExtraInfo() {
        return datasheetGraphExtraInfo;
    }
}
