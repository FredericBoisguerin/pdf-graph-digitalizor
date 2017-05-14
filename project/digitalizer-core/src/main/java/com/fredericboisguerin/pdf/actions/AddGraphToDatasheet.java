package com.fredericboisguerin.pdf.actions;

import java.util.UUID;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetGraph;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

public class AddGraphToDatasheet {

    private final UUID datasheetId;
    private final PDFFile pdfFile;

    public AddGraphToDatasheet(UUID datasheetId, PDFFile pdfFile) {
        this.datasheetId = datasheetId;
        this.pdfFile = pdfFile;
    }

    public void execute(DatasheetService datasheetService) {
        datasheetService.addGraphFromPDF(datasheetId.toString(), new DatasheetGraph(pdfFile));
    }
}
