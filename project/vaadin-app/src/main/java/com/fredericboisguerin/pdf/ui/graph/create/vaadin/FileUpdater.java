package com.fredericboisguerin.pdf.ui.graph.create.vaadin;

import com.fredericboisguerin.pdf.ui.graph.create.FileUpdaterListener;
import com.fredericboisguerin.pdf.ui.upload.FileDropBox;
import com.vaadin.ui.*;

class FileUpdater extends VerticalLayout {
    private final Label filenameLabel = new Label();
    private FileUpdaterListener listener;

    FileUpdater() {
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Component fileDropBox = buildDropPane();
        Component fileUpdatedLayout = buildFileUpdatedLayout();
        addComponents(fileDropBox, fileUpdatedLayout);
        setMargin(false);
    }

    void setFileUpdateListener(FileUpdaterListener listener) {
        this.listener = listener;
    }

    private Component buildFileUpdatedLayout() {
        HorizontalLayout fileupdatedLayout = new HorizontalLayout(new Label("File updated:"),
                filenameLabel);
        fileupdatedLayout.setSpacing(true);
        return fileupdatedLayout;
    }

    private void setLastFileUpdated(String filename, byte[] bytes) {
        filenameLabel.setValue(filename);
        listener.onFileUpdated(filename, bytes);
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

    void init() {
        filenameLabel.setValue("");
    }
}
