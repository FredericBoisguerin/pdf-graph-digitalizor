package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.model.datasheet.pdf.PDFImage;
import com.fredericboisguerin.pdf.ui.graph.create.model.graphinfo.DatasheetGraphInfoViewModel;

public interface CreateDatasheetGraphView {
    void setListener(CreateDatasheetGraphListener listener);

    void setDatasheetInfo(String datasheetInfo);

    void notifyMessage(String message);

    void navigateToReadDatasheet(String datasheetId);

    void setModel(DatasheetGraphInfoViewModel model);

    void displayErrorImpossibleToCropFile();

    void setImageToCrop(PDFImage pdfImage);

    void displayPleaseCropFirst();
}
