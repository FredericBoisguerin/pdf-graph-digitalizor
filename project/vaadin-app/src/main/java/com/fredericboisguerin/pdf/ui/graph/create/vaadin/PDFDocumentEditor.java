package com.fredericboisguerin.pdf.ui.graph.create.vaadin;

import com.fredericboisguerin.pdf.model.datasheet.pdf.ImageCrop;
import com.fredericboisguerin.pdf.model.datasheet.pdf.ImageCropPoint;
import com.fredericboisguerin.pdf.model.datasheet.pdf.ImageCropPointCoord;
import com.fredericboisguerin.pdf.model.datasheet.pdf.PDFImage;
import com.fredericboisguerin.pdf.ui.graph.create.PDFDocumentEditorListener;
import com.vaadin.server.Sizeable;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.*;
import org.vaadin.jcrop.Jcrop;
import org.vaadin.jcrop.selection.JcropSelection;

import java.io.InputStream;
import java.util.UUID;

class PDFDocumentEditor extends VerticalLayout {
    private final Jcrop jcrop = new Jcrop();

    private PDFDocumentEditorListener listener;
    private PDFImage pdfImage;

    PDFDocumentEditor() {
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        jcrop.addListener(this::onCropChanged);
        Button clearCropButton = new Button("Clear selection", this::onClearSelectionClicked);
        addComponents(jcrop, clearCropButton);

        init();
        setMargin(false);
    }

    void setImageToCrop(PDFImage pdfImage) {
        this.pdfImage = pdfImage;
        setPdfImageResource();
        jcrop.setVisible(true);
        setCropDimensions(pdfImage.getWidth().inPixels(), pdfImage.getHeight().inPixels());

    }

    private void setPdfImageResource() {
        String resourceName = UUID.randomUUID().toString();
        StreamResource resource = new StreamResource(pdfImage::asInputStream, resourceName);
        jcrop.setResource(resource);
    }

    void init() {
        pdfImage = null;
        setDefaultImageToCrop();
    }

    private void setDefaultImageToCrop() {
        jcrop.setResource(new StreamResource(this::getDefaultImageStream, UUID.randomUUID().toString()));
        setCropDimensions(454, 340);
    }

    private void setCropDimensions(int width, int height) {
        jcrop.setWidth(width, Unit.PIXELS);
        jcrop.setHeight(height, Unit.PIXELS);
    }

    private InputStream getDefaultImageStream() {
        return getClass().getResourceAsStream("/upload_file_to_start.png");
    }

    private void onClearSelectionClicked(Button.ClickEvent clickEvent) {
        jcrop.clearSelection();
        // I don't know why this is mandatory to enable the component after a clear of the selection
        setPdfImageResource();
        listener.onCropReset();
    }

    private void onCropChanged(JcropSelection jcropSelection) {
        float x1 = ((float) jcropSelection.getX()) / pdfImage.getWidth().inPixels();
        float y1 = 1 - ((float) jcropSelection.getY()) / pdfImage.getHeight().inPixels();
        float x2 = ((float) (jcropSelection.getX() + jcropSelection.getWidth())) / pdfImage.getWidth().inPixels();
        float y2 = 1 - ((float) (jcropSelection.getY() + jcropSelection.getHeight())) / pdfImage.getHeight().inPixels();
        ImageCropPointCoord x2ratio = new ImageCropPointCoord(x2);
        ImageCropPointCoord y1ratio = new ImageCropPointCoord(y1);
        ImageCropPointCoord x1ratio = new ImageCropPointCoord(x1);
        ImageCropPointCoord y2ratio = new ImageCropPointCoord(y2);
        ImageCropPoint lowerLeft = new ImageCropPoint(x1ratio, y2ratio);
        ImageCropPoint upperRight = new ImageCropPoint(x2ratio, y1ratio);
        ImageCrop imageCrop = new ImageCrop(lowerLeft, upperRight);
        System.out.println(imageCrop);
        listener.onCropSelection(imageCrop);
    }

    void setListener(PDFDocumentEditorListener listener) {
        this.listener = listener;
    }
}
