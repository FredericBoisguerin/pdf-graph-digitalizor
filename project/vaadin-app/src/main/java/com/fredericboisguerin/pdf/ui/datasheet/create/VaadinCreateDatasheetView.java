package com.fredericboisguerin.pdf.ui.datasheet.create;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class VaadinCreateDatasheetView extends VerticalLayout implements CreateDatasheetView, View {

    public static final String VIEW_NAME = "create-datasheet";

    private final TextField referenceTextField;
    private final TextField supplierTextField;

    private CreateDatasheetViewListener listener;
    private Navigator navigator;

    public VaadinCreateDatasheetView() {
        Label title = new Label("Create a new datasheet");
        title.addStyleName(ValoTheme.LABEL_HUGE);

        referenceTextField = new TextField("Reference");
        supplierTextField = new TextField("Supplier");

        Button validateButton = new Button("Validate");
        validateButton.addClickListener(this::onValidateButtonClicked);

        addComponents(title, referenceTextField, supplierTextField, validateButton);
    }

    private void onValidateButtonClicked(ClickEvent clickEvent) {
        String reference = referenceTextField.getValue();
        String supplierName = supplierTextField.getValue();

        listener.onValidateButtonClicked(reference, supplierName);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = viewChangeEvent.getNavigator();
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
    public void navigateToRoot() {
        navigator.navigateTo("");
    }
}
