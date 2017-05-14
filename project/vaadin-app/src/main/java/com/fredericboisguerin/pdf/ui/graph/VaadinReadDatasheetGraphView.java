package com.fredericboisguerin.pdf.ui.graph;

import java.util.List;
import java.util.Optional;

import com.fredericboisguerin.pdf.ui.datasheet.extract.VaadinExtractDatasheetDataView;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class VaadinReadDatasheetGraphView extends VerticalLayout
        implements ReadDatasheetGraphView, View {
    public static final String VIEW_NAME = "read-datasheet-graphs";

    private final Grid<DatasheetGraphViewModel> grid = new Grid<>();
    private ReadDatasheetGraphViewListener listener;
    private Navigator navigator;
    private final Label title = new Label();

    public VaadinReadDatasheetGraphView() {
        title.addStyleName(ValoTheme.LABEL_HUGE);

        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(DatasheetGraphViewModel::getFilename)
            .setId("Filename")
            .setCaption("Filename");
        grid.setSizeFull();

        Button extractDataButton = new Button("Extract data");
        extractDataButton.addClickListener(event -> notifyExtractButtonClicked());

        addComponents(title, grid, extractDataButton);
    }

    private void notifyExtractButtonClicked() {
        Optional<DatasheetGraphViewModel> datasheetViewModel = grid.asSingleSelect()
                                                                   .getOptionalValue();
        listener.onDatasheetSelectedForExtraction(datasheetViewModel);
    }

    @Override
    public void setListener(ReadDatasheetGraphViewListener readDatasheetPresenter) {
        this.listener = readDatasheetPresenter;
    }

    @Override
    public void setDatasheetInfo(String datasheetInfo) {
        title.setValue("All graphs for " + datasheetInfo);
    }

    @Override
    public void setDatasheets(List<DatasheetGraphViewModel> datasheetGraphViewModelList) {
        DataProvider<DatasheetGraphViewModel, SerializablePredicate<DatasheetGraphViewModel>> dataProvider = new ListDataProvider<>(
                datasheetGraphViewModelList);
        grid.setDataProvider(dataProvider);
    }

    @Override
    public void notifyTray(String message) {
        Notification.show(message, Notification.Type.TRAY_NOTIFICATION);
    }

    @Override
    public void navigateToExtractDatasheetData(String datasheetId, String graphId) {
        navigator.navigateTo(
                VaadinExtractDatasheetDataView.VIEW_NAME + "/" + datasheetId + "/" + graphId);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        listener.onViewEntered(viewChangeEvent.getParameters());
        navigator = viewChangeEvent.getNavigator();
    }
}
