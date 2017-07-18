package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.model.datasheet.pdf.ImageCrop;
import com.fredericboisguerin.pdf.model.datasheet.pdf.ImageCropPoint;
import com.fredericboisguerin.pdf.model.datasheet.pdf.ImageCropPointCoord;
import com.fredericboisguerin.pdf.model.datasheet.pdf.PDFImage;
import com.fredericboisguerin.pdf.ui.upload.FileDropBox;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.*;
import org.vaadin.jcrop.Jcrop;
import org.vaadin.jcrop.selection.JcropSelection;

import java.util.UUID;

class PDFDocumentEditor extends VerticalLayout {
    private final Jcrop jcrop = new Jcrop();
    private final Label filenameLabel = new Label();

    private PDFDocumentEditorListener listener;
    private PDFImage pdfImage;

    PDFDocumentEditor() {
        Component fileDropBox = buildDropPane();
        Component fileUpdatedLayout = buildFileUpdatedLayout();
        VerticalLayout left = new VerticalLayout(fileDropBox, fileUpdatedLayout);
        left.setMargin(false);

        jcrop.addListener(this::onCropChanged);
        Button clearCropButton = new Button("Clear selection", this::onClearSelectionClicked);
        VerticalLayout right = new VerticalLayout(jcrop, clearCropButton);
        right.setMargin(false);

        addComponents(left, right);

        init();
        setMargin(false);
    }

    void setImageToCrop(PDFImage pdfImage) {
        this.pdfImage = pdfImage;
        setPdfImageResource();
        jcrop.setVisible(true);
        jcrop.setWidth(pdfImage.getWidth().inPixels(), Unit.PIXELS);
        jcrop.setHeight(pdfImage.getHeight().inPixels(), Unit.PIXELS);

    }

    private void setPdfImageResource() {
        String resourceName = UUID.randomUUID().toString();
        StreamResource resource = new StreamResource(pdfImage::asInputStream, resourceName);
        jcrop.setResource(resource);
    }

    void init() {
        filenameLabel.setValue("");
        pdfImage = null;
        jcrop.setVisible(false);
    }

    private void setLastFileUpdated(String filename, byte[] bytes) {
        filenameLabel.setValue(filename);
        listener.onFileUpdated(filename, bytes);
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

    private Component buildDropPane() {
        Label infoLabel = new Label("Drop your file here");

        VerticalLayout dropPane = new VerticalLayout(infoLabel);
        dropPane.setComponentAlignment(infoLabel, Alignment.MIDDLE_CENTER);
        dropPane.setWidth(100, Unit.PERCENTAGE);
        dropPane.addStyleName("drop-area");

        FileDropBox dropBox = new FileDropBox(dropPane);
        dropBox.setFileUpdateListener(this::setLastFileUpdated);
        dropBox.setSizeUndefined();
        return dropBox;
    }

    private Component buildFileUpdatedLayout() {
        HorizontalLayout fileupdatedLayout = new HorizontalLayout(new Label("File updated:"),
                filenameLabel);
        fileupdatedLayout.setSpacing(true);
        return fileupdatedLayout;
    }

    void setListener(PDFDocumentEditorListener listener) {
        this.listener = listener;
    }
}
