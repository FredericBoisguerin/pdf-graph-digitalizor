package com.fredericboisguerin.pdf.ui.graph.create;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class VaadinCreateDatasheetGraphView extends VerticalLayout
        implements CreateDatasheetGraphView, View {
    public static final String VIEW_NAME = "create-datasheet-graph";
    private CreateDatasheetGraphListener listener;
    private final Label title = new Label();

    public VaadinCreateDatasheetGraphView() {
        title.addStyleName(ValoTheme.LABEL_HUGE);

        addComponent(title);
    }

    @Override
    public void setListener(CreateDatasheetGraphListener listener) {
        this.listener = listener;
    }

    @Override
    public void setDatasheetInfo(String datasheetInfo) {
        title.setValue("Add a graph for " + datasheetInfo);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        listener.onViewEntered(event.getParameters());
    }
}
