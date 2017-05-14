package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.ui.graph.list.VaadinReadDatasheetGraphView;
import com.fredericboisguerin.pdf.ui.upload.FileDropBox;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class VaadinCreateDatasheetGraphView extends VerticalLayout
        implements CreateDatasheetGraphView, View {
    public static final String VIEW_NAME = "create-datasheet-graph";

    private final Label title = new Label();
    private final Label filenameLabel = new Label();

    private CreateDatasheetGraphListener listener;
    private String lastFileNameUpdated;
    private byte[] lastFileUpdated;
    private Navigator navigator;

    public VaadinCreateDatasheetGraphView() {
        title.addStyleName(ValoTheme.LABEL_HUGE);

        FileDropBox components = (FileDropBox) buildDropPane();

        Component fileUpdatedLayout = buildFileUpdatedLayout();

        Button validateButton = new Button("Validate");
        validateButton.addClickListener(this::onValidateButtonClicked);

        addComponents(title, components, fileUpdatedLayout, validateButton);
    }

    private void onValidateButtonClicked(ClickEvent clickEvent) {
        listener.onValidateButtonClicked(lastFileNameUpdated, lastFileUpdated);
    }

    private Component buildDropPane() {
        Label infoLabel = new Label("Drop your file here");
        infoLabel.setWidth(240.0f, Unit.PIXELS);

        VerticalLayout dropPane = new VerticalLayout(infoLabel);
        dropPane.setComponentAlignment(infoLabel, Alignment.MIDDLE_CENTER);
        dropPane.setWidth(280.0f, Unit.PIXELS);
        dropPane.setHeight(200.0f, Unit.PIXELS);
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

    private void setLastFileUpdated(String filename, byte[] bytes) {
        this.lastFileNameUpdated = filename;
        filenameLabel.setValue(filename);
        this.lastFileUpdated = bytes;
    }

    @Override
    public void setListener(CreateDatasheetGraphListener listener) {
        this.listener = listener;
    }

    @Override
    public void setDatasheetInfo(String datasheetInfo) {
        title.setValue("Add a graph for " + datasheetInfo);
    }

    @Override
    public void notifyMessage(String message) {
        Notification.show(message);
    }

    @Override
    public void navigateToReadDatasheet(String datasheetId) {
        navigator.navigateTo(VaadinReadDatasheetGraphView.VIEW_NAME + "/" + datasheetId);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        navigator = event.getNavigator();
        listener.onViewEntered(event.getParameters());
    }
}
