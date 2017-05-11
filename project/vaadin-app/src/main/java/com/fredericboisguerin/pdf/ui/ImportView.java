package com.fredericboisguerin.pdf.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ImportView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "import";

    private final TextField referenceTextField;
    private final TextField supplierTextField;
    private final Label filenameLabel;

    private ImportViewListener listener;
    private String lastFileNameUpdated;
    private byte[] lastFileUpdated;

    public ImportView() {
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

    }

    public void setListener(ImportViewListener listener) {
        this.listener = listener;
    }
}
