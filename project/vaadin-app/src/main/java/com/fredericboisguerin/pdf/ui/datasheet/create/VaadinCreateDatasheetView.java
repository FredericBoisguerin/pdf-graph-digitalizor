package com.fredericboisguerin.pdf.ui.datasheet.create;

import com.fredericboisguerin.pdf.ui.NavigationBar;
import com.fredericboisguerin.pdf.ui.Title;
import com.fredericboisguerin.pdf.ui.datasheet.DatasheetMetaInfoForm;
import com.fredericboisguerin.pdf.ui.datasheet.read.VaadinReadDatasheetView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class VaadinCreateDatasheetView extends HorizontalLayout implements CreateDatasheetView, View {

    public static final String VIEW_NAME = "create-datasheet";

    private CreateDatasheetViewListener listener;
    private Navigator navigator;
    private final DatasheetMetaInfoForm datasheetMetaInfoForm = new DatasheetMetaInfoForm();

    public VaadinCreateDatasheetView(NavigationBar navigationBar) {
        Label title = new Title("Create a new datasheet");

        Button validateButton = new Button("Validate");
        validateButton.addClickListener(this::onValidateButtonClicked);

        VerticalLayout content = new VerticalLayout(title, datasheetMetaInfoForm, validateButton);
        addComponents(navigationBar, content);
        setExpandRatio(content, 1f);
        setHeight("100%");
    }

    private void onValidateButtonClicked(ClickEvent clickEvent) {
        DatasheetMetaInfoForm.Model model = datasheetMetaInfoForm.getViewModel();
        String reference = model.getReference();
        String supplierName = model.getSupplier();
        listener.onValidateButtonClicked(reference, supplierName);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = viewChangeEvent.getNavigator();
        datasheetMetaInfoForm.clearFields();
    }

    @Override
    public void setListener(CreateDatasheetViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyMessage(String message) {
        Notification.show(message);
    }

    @Override
    public void navigateToReadDatasheet() {
        navigator.navigateTo(VaadinReadDatasheetView.VIEW_NAME);
    }
}
