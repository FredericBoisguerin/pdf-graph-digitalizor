package com.fredericboisguerin.pdf.ui;

import com.fredericboisguerin.pdf.ui.create.VaadinImportView;
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
        Button goImportButton = new Button("Import");
        goImportButton.addClickListener(clickEvent -> navigateTo(VaadinImportView.VIEW_NAME));
        addComponent(goImportButton);
    }

    private void navigateTo(String viewName) {
        navigator.navigateTo(viewName);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        navigator = viewChangeEvent.getNavigator();
    }
}
