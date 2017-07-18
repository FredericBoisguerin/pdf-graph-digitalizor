package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.model.datasheet.pdf.PDFImage;
import com.fredericboisguerin.pdf.ui.graph.list.VaadinReadDatasheetGraphView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

public class VaadinCreateDatasheetGraphView extends VerticalLayout
        implements CreateDatasheetGraphView, View {
    public static final String VIEW_NAME = "create-datasheet-graph";

    private final Label title = new Label();
    private final PDFDocumentEditor pdfDocumentEditor = new PDFDocumentEditor();
    private final DatasheetGraphInfoEditor graphInfoEditor = new DatasheetGraphInfoEditor();

    private CreateDatasheetGraphListener listener;
    private Navigator navigator;

    public VaadinCreateDatasheetGraphView() {
        title.addStyleName(ValoTheme.LABEL_HUGE);

        pdfDocumentEditor.setSizeFull();

        Button validateButton = new Button("Validate");
        validateButton.addClickListener(this::onValidateButtonClicked);
        VerticalLayout form = new VerticalLayout(graphInfoEditor, validateButton);
        form.setSizeUndefined();

        HorizontalLayout mainLayout = new HorizontalLayout(form, pdfDocumentEditor);
        mainLayout.setExpandRatio(pdfDocumentEditor, 1);
        mainLayout.setSizeFull();

        addComponents(title, mainLayout);
    }

    private void onValidateButtonClicked(ClickEvent clickEvent) {
        if (graphInfoEditor.valid()) {
            listener.onValidateButtonClicked();
        }
    }

    @Override
    public void setListener(CreateDatasheetGraphListener listener) {
        this.listener = listener;
        pdfDocumentEditor.setListener(listener);
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
    public void setModel(CreateDatasheetGraphViewModel model) {
        graphInfoEditor.setModel(model);
    }

    @Override
    public void displayErrorImpossibleToCropFile() {
        displayError("Impossible to crop PDF file");
    }

    private void displayError(String errorMessage) {
        Notification.show(errorMessage, Type.ERROR_MESSAGE);
    }

    @Override
    public void setImageToCrop(PDFImage pdfImage) {
        pdfDocumentEditor.setImageToCrop(pdfImage);
    }

    @Override
    public void displayPleaseCropFirst() {
        displayError("Please select an area of the PDF");
    }

    @Override
    public void enter(ViewChangeEvent event) {
        navigator = event.getNavigator();
        pdfDocumentEditor.init();
        listener.onViewEntered(event.getParameters());
    }

}
