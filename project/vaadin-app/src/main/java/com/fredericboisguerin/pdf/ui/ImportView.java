package com.fredericboisguerin.pdf.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ImportView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "import";


    public ImportView() {
        addComponent(new Label("You are at the import page."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
