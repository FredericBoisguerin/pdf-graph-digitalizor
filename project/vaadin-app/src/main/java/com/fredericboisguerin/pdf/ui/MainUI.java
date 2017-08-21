package com.fredericboisguerin.pdf.ui;

import javax.servlet.annotation.WebServlet;

import com.fredericboisguerin.pdf.infrastructure.InMemoryDatasheetRepository;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.ui.datasheet.create.CreateDatasheetPresenter;
import com.fredericboisguerin.pdf.ui.datasheet.create.VaadinCreateDatasheetView;
import com.fredericboisguerin.pdf.ui.datasheet.edit.EditDatasheetPresenter;
import com.fredericboisguerin.pdf.ui.datasheet.edit.VaadinEditDatasheetView;
import com.fredericboisguerin.pdf.ui.datasheet.extract.ExtractDatasheetDataPresenter;
import com.fredericboisguerin.pdf.ui.datasheet.extract.VaadinExtractDatasheetDataView;
import com.fredericboisguerin.pdf.ui.datasheet.read.ReadDatasheetPresenter;
import com.fredericboisguerin.pdf.ui.datasheet.read.VaadinReadDatasheetView;
import com.fredericboisguerin.pdf.ui.graph.create.CreateDatasheetGraphPresenter;
import com.fredericboisguerin.pdf.ui.graph.create.vaadin.VaadinCreateDatasheetGraphView;
import com.fredericboisguerin.pdf.ui.graph.list.ReadDatasheetGraphPresenter;
import com.fredericboisguerin.pdf.ui.graph.list.VaadinReadDatasheetGraphView;
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

    private static final InMemoryDatasheetRepository datasheetRepository = new InMemoryDatasheetRepository();
    private Navigator navigator;

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("PDF Digitalizer");
        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // 1. START VIEW
        navigator.addView("", new StartView());

        // 2. DATASHEET
        DatasheetService datasheetService = new DatasheetService(datasheetRepository);
        addCreateDatasheetView(datasheetService);
        addEditDatasheetView(datasheetService);
        addReadDatasheetView(datasheetService);
        addReadDatasheetGraphView(datasheetService);
        addExtractInformationsView(datasheetService);
        addCreateDatasheetGraphView(datasheetService);
    }

    private void addEditDatasheetView(DatasheetService datasheetService) {
        VaadinEditDatasheetView view = new VaadinEditDatasheetView(navigationBar());
        EditDatasheetPresenter editDatasheetPresenter = new EditDatasheetPresenter(datasheetService, view);
        view.setListener(editDatasheetPresenter);
        navigator.addView(VaadinEditDatasheetView.VIEW_NAME, view);
    }

    private void addCreateDatasheetGraphView(DatasheetService datasheetService) {
        VaadinCreateDatasheetGraphView view = new VaadinCreateDatasheetGraphView(navigationBar());
        CreateDatasheetGraphPresenter presenter = new CreateDatasheetGraphPresenter(view,
                datasheetService);
        view.setListener(presenter);
        navigator.addView(VaadinCreateDatasheetGraphView.VIEW_NAME, view);
    }

    private void addReadDatasheetGraphView(DatasheetService datasheetService) {
        VaadinReadDatasheetGraphView vaadinReadDatasheetGraphView = new VaadinReadDatasheetGraphView(navigationBar());
        ReadDatasheetGraphPresenter readDatasheetGraphPresenter = new ReadDatasheetGraphPresenter(
                vaadinReadDatasheetGraphView, datasheetService);
        vaadinReadDatasheetGraphView.setListener(readDatasheetGraphPresenter);
        navigator.addView(VaadinReadDatasheetGraphView.VIEW_NAME, vaadinReadDatasheetGraphView);
    }

    private void addExtractInformationsView(DatasheetService datasheetService) {
        VaadinExtractDatasheetDataView extractDatasheetDataView = new VaadinExtractDatasheetDataView(navigationBar());
        extractDatasheetDataView.setListener(
                new ExtractDatasheetDataPresenter(extractDatasheetDataView, datasheetService));
        navigator.addView(VaadinExtractDatasheetDataView.VIEW_NAME, extractDatasheetDataView);
    }

    private void addCreateDatasheetView(DatasheetService datasheetService) {
        VaadinCreateDatasheetView importDatasheetView = new VaadinCreateDatasheetView(
                navigationBar());
        importDatasheetView.setListener(
                new CreateDatasheetPresenter(importDatasheetView, datasheetService));
        navigator.addView(VaadinCreateDatasheetView.VIEW_NAME, importDatasheetView);
    }

    private void addReadDatasheetView(DatasheetService datasheetService) {
        VaadinReadDatasheetView vaadinReadDatasheetView = new VaadinReadDatasheetView();
        ReadDatasheetPresenter readDatasheetPresenter = new ReadDatasheetPresenter(
                vaadinReadDatasheetView, datasheetService);
        vaadinReadDatasheetView.setListener(readDatasheetPresenter);
        navigator.addView(VaadinReadDatasheetView.VIEW_NAME, vaadinReadDatasheetView);
    }

    private NavigationBar navigationBar() {
        return new NavigationBar(navigator);
    }
}
