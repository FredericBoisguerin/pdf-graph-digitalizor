package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.io.InputStream;

public interface ExtractDatasheetDataViewListener {
    void onViewEntered(String datasheetId, String graphId);

    InputStream getExportInputStream();
}
