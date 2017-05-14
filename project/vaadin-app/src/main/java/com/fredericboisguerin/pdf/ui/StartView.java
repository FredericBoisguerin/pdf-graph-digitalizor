package com.fredericboisguerin.pdf.ui;

import com.fredericboisguerin.pdf.ui.datasheet.create.VaadinCreateDatasheetView;
import com.fredericboisguerin.pdf.ui.datasheet.read.VaadinReadDatasheetView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class StartView extends VerticalLayout implements View {

    private Navigator navigator;

    public StartView() {
        String text = "Hello. You're in StartView. There's nothing to do here.";
        addComponent(new Label(text));
        Button importDatasheetButton = buildNavigationButton("Create a datasheet", VaadinCreateDatasheetView.VIEW_NAME);
        Button readDatasheetsButton = buildNavigationButton("View all datasheets", VaadinReadDatasheetView.VIEW_NAME);
        addComponents(importDatasheetButton, readDatasheetsButton);
    }

    private Button buildNavigationButton(String caption, String viewName) {
        Button goImportButton = new Button(caption);
        goImportButton.addClickListener(clickEvent -> navigateTo(viewName));
        return goImportButton;
    }

    private void navigateTo(String viewName) {
        navigator.navigateTo(viewName);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = viewChangeEvent.getNavigator();
    }
}
