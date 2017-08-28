package com.fredericboisguerin.pdf.ui.datasheet.edit;

import com.fredericboisguerin.pdf.ui.NavigationBar;
import com.fredericboisguerin.pdf.ui.Title;
import com.fredericboisguerin.pdf.ui.datasheet.DatasheetMetaInfoForm;
import com.fredericboisguerin.pdf.ui.datasheet.read.VaadinReadDatasheetView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class VaadinEditDatasheetView extends HorizontalLayout implements EditDatasheetView, View {
    public static final String VIEW_NAME = "edit-datasheet";

    private final DatasheetMetaInfoForm form = new DatasheetMetaInfoForm();

    private EditDatasheetViewListener listener;
    private Navigator navigator;

    public VaadinEditDatasheetView(NavigationBar navigationBar) {
        Label title = new Title("Edit datasheet name / supplier");

        Button validateButton = new Button("Validate");
        validateButton.addClickListener(this::onValidateButtonClicked);

        VerticalLayout content = new VerticalLayout(title, form, validateButton);
        addComponents(navigationBar, content);
        setExpandRatio(content, 1f);
        setHeight("100%");
    }

    private void onValidateButtonClicked(Button.ClickEvent clickEvent) {
        DatasheetMetaInfoForm.Model viewModel = form.getViewModel();
        listener.onFormValidated(viewModel.getReference(), viewModel.getSupplier());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = viewChangeEvent.getNavigator();
        listener.onViewEntered(viewChangeEvent.getParameters());
    }

    public void setListener(EditDatasheetViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void setDatasheetInfo(String reference, String supplier) {
        form.setModel(new DatasheetMetaInfoForm.Model(reference, supplier));
    }

    @Override
    public void navigateToReadDatasheetView() {
        navigator.navigateTo(VaadinReadDatasheetView.VIEW_NAME);
    }
}
