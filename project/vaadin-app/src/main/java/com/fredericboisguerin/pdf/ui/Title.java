package com.fredericboisguerin.pdf.ui;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class Title extends Label {
    public Title(String title) {
        super(title);
        addStyleName(ValoTheme.LABEL_HUGE);
    }
}
