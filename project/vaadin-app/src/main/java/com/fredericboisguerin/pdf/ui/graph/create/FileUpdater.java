package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.ui.upload.FileDropBox;
import com.vaadin.ui.*;

public class FileUpdater extends VerticalLayout {
    public final Label filenameLabel = new Label();
    public FileUpdaterListener listener;

    public FileUpdater() {
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Component fileDropBox = buildDropPane();
        Component fileUpdatedLayout = buildFileUpdatedLayout();
        addComponents(fileDropBox, fileUpdatedLayout);
        setMargin(false);
    }

    void setFileUpdateListener(FileUpdaterListener listener) {
        this.listener = listener;
    }

    Component buildFileUpdatedLayout() {
        HorizontalLayout fileupdatedLayout = new HorizontalLayout(new Label("File updated:"),
                filenameLabel);
        fileupdatedLayout.setSpacing(true);
        return fileupdatedLayout;
    }

    private void setLastFileUpdated(String filename, byte[] bytes) {
        filenameLabel.setValue(filename);
        listener.onFileUpdated(filename, bytes);
    }

    Component buildDropPane() {
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
