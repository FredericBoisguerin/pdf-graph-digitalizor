package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.model.datasheet.pdf.ImageCrop;

public interface PDFDocumentEditorListener {
    void onFileUpdated(String filename, byte[] bytes);

    void onCropReset();

    void onCropSelection(ImageCrop imageCrop);
}
