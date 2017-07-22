package com.fredericboisguerin.pdf.ui.graph.create;

public interface FileUpdaterListener {
    void onFileUpdated(String filename, byte[] bytes);
}
