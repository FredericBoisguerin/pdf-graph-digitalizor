package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.model.datasheet.pdf.PDFImage;

public interface CreateDatasheetGraphView {
    void setListener(CreateDatasheetGraphListener listener);

    void setDatasheetInfo(String datasheetInfo);

    void notifyMessage(String message);

    void navigateToReadDatasheet(String datasheetId);

    void setModel(CreateDatasheetGraphViewModel model);

    void displayErrorImpossibleToCropFile();

    void setImageToCrop(PDFImage pdfImage);

    void displayPleaseCropFirst();
}
