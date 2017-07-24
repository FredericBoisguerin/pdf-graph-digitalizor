package com.fredericboisguerin.pdf.ui.graph.create.vaadin;

import com.fredericboisguerin.pdf.model.datasheet.pdf.PDFImage;
import com.fredericboisguerin.pdf.ui.graph.create.CreateDatasheetGraphListener;
import com.fredericboisguerin.pdf.ui.graph.create.CreateDatasheetGraphView;
import com.fredericboisguerin.pdf.ui.graph.create.model.graphinfo.DatasheetGraphInfoViewModel;
import com.fredericboisguerin.pdf.ui.graph.create.vaadin.graphinfo.VaadinDatasheetGraphInfoEditor;
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
    private final FileUpdater fileUpdater = new FileUpdater();
    private final PDFDocumentEditor pdfDocumentEditor = new PDFDocumentEditor();
    private final VaadinDatasheetGraphInfoEditor graphInfoEditor = new VaadinDatasheetGraphInfoEditor();

    private CreateDatasheetGraphListener listener;
    private Navigator navigator;

    public VaadinCreateDatasheetGraphView() {
        title.addStyleName(ValoTheme.LABEL_HUGE);

        pdfDocumentEditor.setSizeFull();

        Button validateButton = new Button("Validate");
        validateButton.addClickListener(this::onValidateButtonClicked);
        VerticalLayout form = new VerticalLayout(graphInfoEditor, validateButton);
        form.setSizeUndefined();

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        mainLayout.addComponents(fileUpdater, pdfDocumentEditor, form);
        mainLayout.setExpandRatio(fileUpdater, 1);
        mainLayout.setExpandRatio(pdfDocumentEditor, 1);
        mainLayout.setExpandRatio(form, 1);
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
        fileUpdater.setFileUpdateListener(listener);
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
    public void setModel(DatasheetGraphInfoViewModel model) {
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
    public void enter(ViewChangeEvent event) {
        navigator = event.getNavigator();
        fileUpdater.init();
        pdfDocumentEditor.init();
        listener.onViewEntered(event.getParameters());
    }

}
