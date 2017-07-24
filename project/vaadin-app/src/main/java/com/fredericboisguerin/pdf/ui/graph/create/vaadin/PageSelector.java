package com.fredericboisguerin.pdf.ui.graph.create.vaadin;

import com.fredericboisguerin.pdf.ui.graph.create.PageSelectionListener;
import com.fredericboisguerin.pdf.ui.graph.create.model.PageSelectionModel;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class PageSelector extends HorizontalLayout {
    private final Button incButton;
    private final Button decButton;
    private final Label label = new Label();
    private PageSelectionListener listener;
    private PageSelectionModel model;

    public PageSelector() {
        decButton = buildActionButton("<", event -> onDecPressed());
        incButton = buildActionButton(">", event -> onIncPressed());
        addComponents(decButton, label, incButton);
    }

    private Button buildActionButton(String caption, Button.ClickListener clickListener) {
        Button button = new Button(caption, clickListener);
        button.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        return button;
    }

    private void onIncPressed() {
        model.inc();
        updateButtonsStateAndLabel();
        notifyListener();
    }

    private void onDecPressed() {
        model.dec();
        updateButtonsStateAndLabel();
        notifyListener();
    }

    private void notifyListener() {
        listener.onPageSelected(model.getSelectedPage());
    }

    public void setListener(PageSelectionListener listener) {
        this.listener = listener;
    }

    private void updateButtonsStateAndLabel() {
        incButton.setEnabled(model.isIncable());
        decButton.setEnabled(model.isDecable());
        label.setValue(model.asString());
    }

    public void setModel(PageSelectionModel model) {
        this.model = model;
        updateButtonsStateAndLabel();
    }
}
