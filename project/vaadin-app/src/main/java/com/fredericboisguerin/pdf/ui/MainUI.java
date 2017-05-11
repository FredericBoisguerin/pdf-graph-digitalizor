package com.fredericboisguerin.pdf.ui;

import javax.servlet.annotation.WebServlet;

import com.fredericboisguerin.pdf.infrastructure.InMemoryDatasheetRepository;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MainUI extends UI {

    Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("PDF Digitalizer");
        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // START VIEW
        navigator.addView("", new StartView());
        ImportView importView = new ImportView();

        // IMPORT VIEW
        DatasheetService datasheetService = new DatasheetService(new InMemoryDatasheetRepository());
        importView.setListener(new ImportPresenter(datasheetService));
        navigator.addView(ImportView.VIEW_NAME, importView);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
