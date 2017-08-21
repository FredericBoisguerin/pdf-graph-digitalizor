package com.fredericboisguerin.pdf.ui.datasheet.extract;

import com.fredericboisguerin.pdf.ui.NavigationBar;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class VaadinExtractDatasheetDataView extends HorizontalLayout
        implements View, ExtractDatasheetDataView {

    private static final String INFO_LABEL_FORMAT = "Extraction of data from %s";
    private static final String AXIS_X_CAPTION = "Axis X";
    private static final String AXIS_Y_CAPTION = "Axis Y";

    private final Label title;
    private final VaadinAxisEditorView xAxisView = new VaadinAxisEditorView();
    private final VaadinAxisEditorView yAxisView = new VaadinAxisEditorView();
    private final VaadinSeriesSelectionComponent seriesView = new VaadinSeriesSelectionComponent();
    private final Button exportButton;

    private ExtractDatasheetDataViewListener listener;

    public VaadinExtractDatasheetDataView(NavigationBar navigationBar) {
        title = new Label();
        title.addStyleName(ValoTheme.LABEL_HUGE);

        VerticalLayout xAxisLayout = buildAxisLayout(AXIS_X_CAPTION, xAxisView);
        VerticalLayout yAxisLayout = buildAxisLayout(AXIS_Y_CAPTION, yAxisView);
        HorizontalLayout axesLayout = new HorizontalLayout(xAxisLayout, yAxisLayout);
        axesLayout.setComponentAlignment(xAxisLayout, Alignment.MIDDLE_RIGHT);
        axesLayout.setComponentAlignment(yAxisLayout, Alignment.MIDDLE_LEFT);

        exportButton = buildExportButton();

        axesLayout.setWidth(67, Unit.PERCENTAGE);
        seriesView.setWidth(67, Unit.PERCENTAGE);

        VerticalLayout content = new VerticalLayout();
        content.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        content.addComponents(title, seriesView, axesLayout, exportButton);
        addComponents(navigationBar, content);
        setExpandRatio(content, 1f);
        setSizeFull();

        xAxisView.getFocus();
    }

    private Button buildExportButton() {
        Button export = new Button("Export");
        export.setEnabled(false);
        return export;
    }

    @Override
    public void setExportResource(Resource resource) {
        FileDownloader fileDownloader = new FileDownloader(resource);
        fileDownloader.extend(exportButton);
    }

    private void onStatusChanged() {
        boolean formIsValid = xAxisView.isValid() && yAxisView.isValid();
        exportButton.setEnabled(formIsValid);
    }

    private VerticalLayout buildAxisLayout(String caption, VaadinAxisEditorView axisView) {
        Label label = new Label(caption);
        axisView.addStatusChangeListener(event -> onStatusChanged());
        VerticalLayout layout = new VerticalLayout(label, axisView);
        layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        layout.setSizeUndefined();
        return layout;
    }

    @Override
    public void setListener(ExtractDatasheetDataViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyError(String message) {
        Notification.show(message, Notification.Type.ERROR_MESSAGE);
    }

    @Override
    public void setSeries(List<SerieViewModel> serieViewModels) {
        seriesView.setSeries(serieViewModels);
    }

    @Override
    public void setDatasheetInfo(String datasheetInfo) {
        title.setValue(String.format(INFO_LABEL_FORMAT, datasheetInfo));
    }

    @Override
    public void setAxesViewModel(AxesViewModel axesViewModel) {
        AxisViewModel xAxisModel = axesViewModel.getAxisX();
        xAxisView.setModel(xAxisModel);
        AxisViewModel yAxisModel = axesViewModel.getAxisY();
        yAxisView.setModel(yAxisModel);
    }

    @Override
    public Collection<UUID> getSelectedSeriesIds() {
        return seriesView.getSelectedSeriesIds();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String parameters = event.getParameters();
        String[] split = parameters.split("/");
        seriesView.removeAllSeries();
        listener.onViewEntered(split[0], split[1]);
    }
}
