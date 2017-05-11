package com.fredericboisguerin.pdf.ui;

import com.fredericboisguerin.pdf.infrastructure.InMemoryDatasheetRepository;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.ui.datasheet.create.ImportDatasheetDatasheetPresenter;
import com.fredericboisguerin.pdf.ui.datasheet.create.VaadinImportDatasheetDatasheetView;
import com.fredericboisguerin.pdf.ui.datasheet.read.ReadDatasheetPresenter;
import com.fredericboisguerin.pdf.ui.datasheet.read.VaadinReadDatasheetView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MainUI extends UI {

    private static final InMemoryDatasheetRepository datasheetRepository = new InMemoryDatasheetRepository();
    private Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("PDF Digitalizer");
        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // 1. START VIEW
        navigator.addView("", new StartView());

        // 2. DATASHEET
        DatasheetService datasheetService = new DatasheetService(datasheetRepository);
        addImportDatasheetView(datasheetService);
        addReadDatasheetView(datasheetService);
    }

    private void addImportDatasheetView(DatasheetService datasheetService) {
        VaadinImportDatasheetDatasheetView vaadinImportDatasheetView = new VaadinImportDatasheetDatasheetView();
        vaadinImportDatasheetView.setListener(new ImportDatasheetDatasheetPresenter(vaadinImportDatasheetView, datasheetService));
        navigator.addView(VaadinImportDatasheetDatasheetView.VIEW_NAME, vaadinImportDatasheetView);
    }

    private void addReadDatasheetView(DatasheetService datasheetService) {
        VaadinReadDatasheetView vaadinReadDatasheetView = new VaadinReadDatasheetView();
        ReadDatasheetPresenter readDatasheetPresenter = new ReadDatasheetPresenter(vaadinReadDatasheetView, datasheetService);
        vaadinReadDatasheetView.setListener(readDatasheetPresenter);
        navigator.addView(VaadinReadDatasheetView.VIEW_NAME, vaadinReadDatasheetView);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
