package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.io.InputStream;

public interface ExtractDatasheetDataViewListener {
    void onViewEntered(String parameter);

    InputStream getExportInputStream();
}
