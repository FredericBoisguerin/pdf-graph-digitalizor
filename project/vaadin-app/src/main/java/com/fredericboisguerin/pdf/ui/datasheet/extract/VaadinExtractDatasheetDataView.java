package com.fredericboisguerin.pdf.ui.datasheet.extract;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

public class VaadinExtractDatasheetDataView extends VerticalLayout implements View, ExtractDatasheetDataView {

    private static final String INFO_LABEL_FORMAT = "Extraction of data from %s";

    private final TwinColSelect<SerieViewModel> twinColSelect = new TwinColSelect<>();
    private final Label title;

    private ExtractDatasheetDataViewListener listener;

    public VaadinExtractDatasheetDataView() {
        title = new Label();
        title.addStyleName(ValoTheme.LABEL_HUGE);

        twinColSelect.setWidth(50, Unit.PERCENTAGE);

        addComponents(title, twinColSelect);
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
        ListDataProvider<SerieViewModel> dataProvider = new ListDataProvider<>(serieViewModels);
        twinColSelect.setDataProvider(dataProvider);
    }

    @Override
    public void setDatasheetInfo(String datasheetInfo) {
        title.setValue(String.format(INFO_LABEL_FORMAT, datasheetInfo));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        listener.onViewEntered(event.getParameters());
    }
}
