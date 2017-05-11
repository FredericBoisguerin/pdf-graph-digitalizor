package com.fredericboisguerin.pdf.ui.datasheet.read;

import java.util.List;

public interface ReadDatasheetView {
    void setListener(ReadDatasheetViewListener readDatasheetPresenter);

    void setDatasheets(List<DatasheetViewModel> datasheetViewModelList);
}
