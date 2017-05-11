package com.fredericboisguerin.pdf.ui.datasheet.read;

import com.fredericboisguerin.pdf.ui.datasheet.extract.VaadinExtractDatasheetDataView;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;
import java.util.Optional;

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

        Button extractDataButton = new Button("Extract data");
        extractDataButton.addClickListener(event -> notifyExtractButtonClicked());

        addComponents(title, grid, extractDataButton);
    }

    private void notifyExtractButtonClicked() {
        Optional<DatasheetViewModel> datasheetViewModel = grid
                .asSingleSelect()
                .getOptionalValue();
        listener.onDatasheetSelectedForExtraction(datasheetViewModel);
    }

    @Override
    public void setListener(ReadDatasheetViewListener readDatasheetPresenter) {
        this.listener = readDatasheetPresenter;
    }

    @Override
    public void setDatasheets(List<DatasheetViewModel> datasheetViewModelList) {
        DataProvider<DatasheetViewModel, SerializablePredicate<DatasheetViewModel>> dataProvider =
                new ListDataProvider<>(datasheetViewModelList);
        grid.setDataProvider(dataProvider);
    }

    @Override
    public void notifyTray(String message) {
        Notification.show(message, Notification.Type.TRAY_NOTIFICATION);
    }

    @Override
    public void navigateToExtractDatasheetData(String param) {
        navigator.navigateTo(VaadinExtractDatasheetDataView.VIEW_NAME + "/" + param);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        listener.onViewEntered();
        navigator = viewChangeEvent.getNavigator();
    }
}
