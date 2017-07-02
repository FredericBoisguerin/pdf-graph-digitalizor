package com.fredericboisguerin.pdf.ui;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

public class ButtonBuilder {

    private final Button button = new Button();

    public static ButtonBuilder button() {
        return new ButtonBuilder();
    }

    public ButtonBuilder withCaption(String caption) {
        button.setCaption(caption);
        return this;
    }

    public ButtonBuilder withIcon(Resource resource) {
        button.setIcon(resource);
        return this;
    }

    public ButtonBuilder withStyle(String style) {
        button.addStyleName(style);
        return this;
    }

    public ButtonBuilder withListener(Button.ClickListener onRemoveGraphButtonClicked) {
        button.addClickListener(onRemoveGraphButtonClicked);
        return this;
    }

    public Button build() {
        return button;
    }
}
