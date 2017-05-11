package com.fredericboisguerin.pdf.ui.create;

import com.fredericboisguerin.pdf.ui.upload.FileDropBox;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

public class VaadinImportView extends VerticalLayout implements ImportView, View {

    public static final String VIEW_NAME = "import";

    private final TextField referenceTextField;
    private final TextField supplierTextField;
    private final Label filenameLabel;

    private ImportViewListener listener;
    private String lastFileNameUpdated;
    private byte[] lastFileUpdated;
    private Navigator navigator;

    public VaadinImportView() {
        Label instructionsLabel = new Label("You are at the import page.");

        referenceTextField = new TextField("Reference");
        supplierTextField = new TextField("Supplier");

        FileDropBox components = (FileDropBox) buildDropPane();

        filenameLabel = new Label("none");
        Component fileUpdatedLayout = buildFileUpdatedLayout();

        Button validateButton = new Button("Validate");
        validateButton.addClickListener(this::onValidateButtonClicked);

        addComponents(instructionsLabel, referenceTextField, supplierTextField, components,
                fileUpdatedLayout, validateButton);
    }

    private Component buildFileUpdatedLayout() {
        HorizontalLayout fileupdatedLayout = new HorizontalLayout(new Label("File updated:"),
                filenameLabel);
        fileupdatedLayout.setSpacing(true);
        return fileupdatedLayout;
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

    private void setLastFileUpdated(String filename, byte[] bytes) {
        this.lastFileNameUpdated = filename;
        filenameLabel.setValue(filename);
        this.lastFileUpdated = bytes;
    }

    private void onValidateButtonClicked(ClickEvent clickEvent) {
        String reference = referenceTextField.getValue();
        String supplierName = supplierTextField.getValue();

        listener.onValidateButtonClicked(reference, supplierName, lastFileUpdated,
                lastFileNameUpdated);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = viewChangeEvent.getNavigator();
    }

    public void setListener(ImportViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyMessage(String message) {
        Notification.show(message);
    }

    @Override
    public void navigateTo(String viewName) {
        navigator.navigateTo(viewName);
    }
}
