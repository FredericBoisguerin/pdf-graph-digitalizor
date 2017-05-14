package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetGraph;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

public class AddGraphToDatasheet {

    private final String datasheetId;
    private final PDFFile pdfFile;

    public AddGraphToDatasheet(String datasheetId, PDFFile pdfFile) {
        this.datasheetId = datasheetId;
        this.pdfFile = pdfFile;
    }

    public void execute(DatasheetService datasheetService) {
        datasheetService.addGraphFromPDF(datasheetId, new DatasheetGraph(pdfFile));
    }
}
