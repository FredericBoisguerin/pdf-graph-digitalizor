package com.fredericboisguerin.pdf.ui.graph.list;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.fredericboisguerin.pdf.ui.ButtonBuilder;
import com.fredericboisguerin.pdf.ui.NavigationBar;
import com.fredericboisguerin.pdf.ui.YesNoDialog;
import com.fredericboisguerin.pdf.ui.datasheet.extract.VaadinExtractDatasheetDataView;
import com.fredericboisguerin.pdf.ui.graph.create.vaadin.VaadinCreateDatasheetGraphView;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.*;

import static com.vaadin.ui.themes.ValoTheme.*;

public class VaadinReadDatasheetGraphView extends HorizontalLayout
        implements ReadDatasheetGraphView, View {
    public static final String VIEW_NAME = "read-datasheet-graphs";

    private final Grid<DatasheetGraphViewModel> grid = new Grid<>();
    private ReadDatasheetGraphViewListener listener;
    private Navigator navigator;
    private final Label title = new Label();

    public VaadinReadDatasheetGraphView(NavigationBar navigationBar) {
        title.addStyleName(LABEL_HUGE);

        Button createGraphButton = graphCreationButton();
        prepareGrid();
        Button extractDataButton = extractDataButton();
        Button removeButton = removeGraphButton();
        HorizontalLayout actionsLayout = new HorizontalLayout(extractDataButton, removeButton);

        VerticalLayout content = new VerticalLayout(title, createGraphButton, grid, actionsLayout);
        addComponents(navigationBar, content);
        setExpandRatio(content, 1f);
        setSizeFull();
    }

    private Button removeGraphButton() {
        return ButtonBuilder.button()
                    .withCaption("Remove graph")
                    .withStyle(BUTTON_DANGER)
                    .withIcon(VaadinIcons.TRASH)
                    .withListener(this::onRemoveGraphButtonClicked)
                    .build();
    }

    private Button extractDataButton() {
        return ButtonBuilder.button()
                .withCaption("Extract data")
                .withIcon(VaadinIcons.SPLINE_CHART)
                .withStyle(BUTTON_FRIENDLY)
                .withListener(this::notifyExtractButtonClicked)
                .build();
    }

    private void prepareGrid() {
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addColumn(DatasheetGraphViewModel::getyAxisName)
            .setId("Y")
            .setCaption("Y axis name");
        grid.addColumn(DatasheetGraphViewModel::getxAxisName)
            .setId("X")
            .setCaption("X axis name");
        grid.addColumn(DatasheetGraphViewModel::getFilename)
            .setId("Filename")
            .setCaption("Filename");
        grid.setSizeFull();
    }

    private Button graphCreationButton() {
        return ButtonBuilder.button()
                .withCaption("Add a graph")
                .withIcon(VaadinIcons.FILE_ADD)
                .withStyle(BUTTON_PRIMARY)
                .withListener(this::notifyNewButtonClicked)
                .build();
    }

    private void notifyNewButtonClicked(Button.ClickEvent clickEvent) {
        listener.onNewGraph();
    }

    private void onRemoveGraphButtonClicked(Button.ClickEvent event) {
        executeOrWarn(this::onRemoveGraph);
    }

    private void onRemoveGraph(DatasheetGraphViewModel datasheetGraphViewModel) {
        String question = String.format("This will remove the graph '%s' vs '%s'. Are you sure?",
                datasheetGraphViewModel.getyAxisName(), datasheetGraphViewModel.getxAxisName());
        YesNoDialog confirmation = new YesNoDialog("Confirmation", question, () -> listener.onGraphSelectedForRemoval(datasheetGraphViewModel));
        getUI().addWindow(confirmation);
    }

    private void notifyExtractButtonClicked(Button.ClickEvent event) {
        executeOrWarn(listener::onDatasheetSelectedForExtraction);
    }

    private void executeOrWarn(Consumer<DatasheetGraphViewModel> consumer) {
        Optional<DatasheetGraphViewModel> optionalValue = grid.asSingleSelect()
                                                              .getOptionalValue();
        if (!optionalValue.isPresent()) {
            Notification.show("Please select a datasheet graph to extract data from!", Notification.Type.TRAY_NOTIFICATION);
            return;
        }
        optionalValue.ifPresent(consumer);
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
    public void navigateToExtractDatasheetData(String datasheetId, String graphId) {
        navigateToView(VaadinExtractDatasheetDataView.VIEW_NAME, datasheetId + "/" + graphId);
    }

    private void navigateToView(String viewName, String params) {
        navigator.navigateTo(viewName + "/" + params);
    }

    @Override
    public void navigateToNewGraph(String params) {
        navigateToView(VaadinCreateDatasheetGraphView.VIEW_NAME, params);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        listener.onViewEntered(viewChangeEvent.getParameters());
        navigator = viewChangeEvent.getNavigator();
    }
}
