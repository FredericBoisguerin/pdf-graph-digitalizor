package com.fredericboisguerin.pdf.ui;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class YesNoDialog extends Window {

    private final Button yesButton = new Button("Yes");
    private final Button noButton = new Button("No");
    private final Callback callback;

    public YesNoDialog(String title, String question, Callback callback) {
        super(title);
        this.callback = callback;
        center();
        setModal(true);
        setClosable(false);
        setResizable(false);

        Label questionLabel = new Label(question);

        noButton.addClickListener(this::onNoButtonClicked);
        yesButton.addClickListener(this::onYesButtonClicked);
        yesButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        HorizontalLayout buttonsLayout = new HorizontalLayout(noButton, yesButton);

        VerticalLayout mainLayout = new VerticalLayout(questionLabel, buttonsLayout);

        setContent(mainLayout);
    }

    private void onYesButtonClicked(Button.ClickEvent clickEvent) {
        this.callback.call();
        close();
    }

    private void onNoButtonClicked(Button.ClickEvent clickEvent) {
        Notification.show("Action cancelled");
        close();
    }

    public interface Callback {
        void call();
    }
}
