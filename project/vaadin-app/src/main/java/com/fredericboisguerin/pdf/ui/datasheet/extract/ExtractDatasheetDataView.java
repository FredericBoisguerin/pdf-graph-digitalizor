package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ExtractDatasheetDataView {
    String VIEW_NAME = "extract-datasheet-data";

    void setListener(ExtractDatasheetDataViewListener listener);

    void notifyError(String message);

    void setSeries(List<SerieViewModel> serieViewModels);

    void setDatasheetInfo(String datasheetInfo);

    void setAxesViewModels(AxesViewModel axesViewModel);

    Collection<UUID> getSelectedSeriesIds();

}
