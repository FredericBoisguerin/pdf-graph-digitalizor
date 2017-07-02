package com.fredericboisguerin.pdf.ui.datasheet.read;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.fredericboisguerin.pdf.ui.Icons;
import com.fredericboisguerin.pdf.ui.YesNoDialog;
import com.fredericboisguerin.pdf.ui.datasheet.create.VaadinCreateDatasheetView;
import com.fredericboisguerin.pdf.ui.datasheet.edit.VaadinEditDatasheetView;
import com.fredericboisguerin.pdf.ui.graph.create.VaadinCreateDatasheetGraphView;
import com.fredericboisguerin.pdf.ui.graph.list.VaadinReadDatasheetGraphView;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class VaadinReadDatasheetView extends VerticalLayout implements ReadDatasheetView, View {
    public static final String VIEW_NAME = "read-datasheets";

    private final Grid<DatasheetViewModel> grid = new Grid<>();
    private ReadDatasheetViewListener listener;
    private Navigator navigator;

    public VaadinReadDatasheetView() {
        Label title = new Label("All Datasheets");
        title.addStyleName(ValoTheme.LABEL_HUGE);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(DatasheetViewModel::getReference)
            .setId("DatasheetReference")
            .setCaption("Reference");
        grid.addColumn(DatasheetViewModel::getSupplier)
            .setId("DatasheetSupplier")
            .setCaption("Supplier");
        grid.setSizeFull();

        Button createDatasheetButton = new Button("Create a datasheet");
        createDatasheetButton.setIcon(Icons.FOLDER_ADD);
        createDatasheetButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        createDatasheetButton.addClickListener(event -> listener.onCreateDatasheetButtonClicked());

        Button editNameAndSupplierButton = new Button("Edit name/supplier");
        editNameAndSupplierButton.setIcon(Icons.PENCIL);
        editNameAndSupplierButton.addClickListener(event -> consumeOrWarn(listener::onDatasheetSelectedForEditNameAndSuppplier));

        Button archiveButton = new Button("Archive");
        archiveButton.setIcon(Icons.ARCHIVE);
        archiveButton.addStyleName(ValoTheme.BUTTON_DANGER);
        archiveButton.addClickListener(event -> consumeOrWarn(this::onDatasheetSelectedForArchive));

        Button addGraphButton = new Button("Add a graph");
        addGraphButton.setIcon(Icons.FILE_ADD);
        addGraphButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        addGraphButton.addClickListener(event -> consumeOrWarn(listener::onDatasheetSelectedForAddGraph));

        Button viewGraphsButton = new Button("View graphs");
        viewGraphsButton.setIcon(Icons.LIST_UL);
        viewGraphsButton.addClickListener(event -> consumeOrWarn(listener::onDatasheetSelectedForViewGraphs));

        HorizontalLayout datasheetActions = new HorizontalLayout(addGraphButton, viewGraphsButton, editNameAndSupplierButton);
        HorizontalLayout topActionBar = new HorizontalLayout(createDatasheetButton, archiveButton);
        topActionBar.setExpandRatio(archiveButton, 0);
        topActionBar.setComponentAlignment(archiveButton, Alignment.MIDDLE_RIGHT);
        topActionBar.setWidth(100, Unit.PERCENTAGE);
        addComponents(title, topActionBar, grid, datasheetActions);
    }

    private void onDatasheetSelectedForArchive(DatasheetViewModel datasheetViewModel) {
        String message = String.format("This will delete the datasheet %s (%s). Are you sure?",
                datasheetViewModel.getReference(), datasheetViewModel.getSupplier());
        YesNoDialog confirmation = new YesNoDialog("Confirmation", message, () -> listener.onDatasheetSelectedForArchive(datasheetViewModel));
        getUI().addWindow(confirmation);
    }

    private void consumeOrWarn(Consumer<DatasheetViewModel> modelConsumer) {
        Optional<DatasheetViewModel> selectedDatasheetViewModel = grid.asSingleSelect().getOptionalValue();
        if (!selectedDatasheetViewModel.isPresent()) {
            notifyTray("Please select a datasheet!");
            return;
        }
        selectedDatasheetViewModel.ifPresent(modelConsumer);
    }

    @Override
    public void setListener(ReadDatasheetViewListener readDatasheetPresenter) {
        this.listener = readDatasheetPresenter;
    }

    @Override
    public void setDatasheets(List<DatasheetViewModel> datasheetViewModelList) {
        DataProvider<DatasheetViewModel, SerializablePredicate<DatasheetViewModel>> dataProvider = new ListDataProvider<>(
                datasheetViewModelList);
        grid.setDataProvider(dataProvider);
    }

    @Override
    public void notifyTray(String message) {
        Notification.show(message, Notification.Type.TRAY_NOTIFICATION);
    }

    @Override
    public void navigateToViewDatasheetGraphs(String param) {
        navigateToView(VaadinReadDatasheetGraphView.VIEW_NAME, param);
    }

    private void navigateToView(String viewName, String param) {
        navigator.navigateTo(viewName + "/" + param);
    }

    @Override
    public void navigateToAddDatasheetGraph(String param) {
        navigateToView(VaadinCreateDatasheetGraphView.VIEW_NAME, param);
    }

    @Override
    public void navigateToCreateDatasheet() {
        navigator.navigateTo(VaadinCreateDatasheetView.VIEW_NAME);
    }

    @Override
    public void navigateToEditDatasheet(String param) {
        navigateToView(VaadinEditDatasheetView.VIEW_NAME, param);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        listener.onViewEntered();
        navigator = viewChangeEvent.getNavigator();
    }
}
