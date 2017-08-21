package com.fredericboisguerin.pdf.ui;

import com.fredericboisguerin.pdf.ui.datasheet.read.VaadinReadDatasheetView;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class NavigationBar extends VerticalLayout {

    public NavigationBar(Navigator navigator) {
        setHeight("100%");
        setWidth("190px");
        addStyleName("navigation-bar");

        Title menu = new Title("Menu");

        Button datasheetListButton = ButtonBuilder.button()
                .withCaption("Datasheets list")
                .withStyle(ValoTheme.BUTTON_LINK)
                .withListener(event -> navigator.navigateTo(VaadinReadDatasheetView.VIEW_NAME))
                .build();

        VerticalLayout content = new VerticalLayout(menu, datasheetListButton);
        content.setMargin(false);
        addComponent(content);
    }
}
