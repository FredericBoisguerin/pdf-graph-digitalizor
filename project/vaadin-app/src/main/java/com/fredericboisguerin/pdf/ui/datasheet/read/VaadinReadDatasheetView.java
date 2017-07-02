package com.fredericboisguerin.pdf.ui.datasheet.read;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.fredericboisguerin.pdf.ui.ButtonBuilder;
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

import static com.fredericboisguerin.pdf.ui.Icons.*;
import static com.vaadin.ui.themes.ValoTheme.*;

public class VaadinReadDatasheetView extends VerticalLayout implements ReadDatasheetView, View {
    public static final String VIEW_NAME = "read-datasheets";

    private final Grid<DatasheetViewModel> grid = new Grid<>();
    private ReadDatasheetViewListener listener;
    private Navigator navigator;

    public VaadinReadDatasheetView() {
        Label title = new Label("All Datasheets");
        title.addStyleName(LABEL_HUGE);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(DatasheetViewModel::getReference)
            .setId("DatasheetReference")
            .setCaption("Reference");
        grid.addColumn(DatasheetViewModel::getSupplier)
            .setId("DatasheetSupplier")
            .setCaption("Supplier");
        grid.setSizeFull();

        Button createDatasheetButton = ButtonBuilder.button()
                                                    .withCaption("Create a datasheet")
                                                    .withIcon(FOLDER_ADD)
                                                    .withStyle(BUTTON_PRIMARY)
                                                    .withListener(event -> listener.onCreateDatasheetButtonClicked())
                                                    .build();

        Button editNameAndSupplierButton = ButtonBuilder.button()
                                                        .withCaption("Edit name/supplier")
                                                        .withIcon(PENCIL)
                                                        .withListener(event -> consumeOrWarn(listener::onDatasheetSelectedForEditNameAndSuppplier))
                                                        .build();

        Button archiveButton = ButtonBuilder.button()
                                            .withCaption("Archive")
                                            .withIcon(ARCHIVE)
                                            .withStyle(BUTTON_DANGER)
                                            .withListener(event -> consumeOrWarn(this::onDatasheetSelectedForArchive))
                                            .build();

        Button addGraphButton = ButtonBuilder.button()
                                             .withCaption("Add a graph")
                                             .withIcon(FILE_ADD)
                                             .withStyle(BUTTON_FRIENDLY)
                                             .withListener(event -> consumeOrWarn(listener::onDatasheetSelectedForAddGraph))
                                             .build();

        Button viewGraphsButton = ButtonBuilder.button()
                                               .withCaption("View graphs")
                                               .withIcon(LIST_UL)
                                               .withListener(event -> consumeOrWarn(listener::onDatasheetSelectedForViewGraphs))
                                               .build();

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
