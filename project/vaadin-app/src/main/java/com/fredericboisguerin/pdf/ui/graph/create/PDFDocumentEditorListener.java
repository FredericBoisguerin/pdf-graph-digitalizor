package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.model.datasheet.pdf.ImageCrop;

public interface PDFDocumentEditorListener {

    void onCropReset();

    void onCropSelection(ImageCrop imageCrop);
}
