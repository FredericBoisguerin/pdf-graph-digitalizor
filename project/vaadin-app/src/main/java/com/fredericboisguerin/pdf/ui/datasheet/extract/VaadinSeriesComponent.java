package com.fredericboisguerin.pdf.ui.datasheet.extract;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class VaadinSeriesComponent extends HorizontalLayout {

    private final Chart chart = new Chart(ChartType.LINE);
    private final Set<Integer> selectedSeriesIds = new HashSet<>();

    VaadinSeriesComponent() {
        Button unselectAllButton = new Button("Unselect all");
        unselectAllButton.addClickListener(event -> unselectAllSeries());

        Configuration configuration = chart.getConfiguration();
        configuration.setTitle("");
        configuration.setSubTitle("Select / unselect the series by clicking on the legend items");
        chart.addSeriesHideListener(event -> onSeriesHiden(event.getSeries()));
        chart.addSeriesShowListener(event -> onSeriesShown(event.getSeries()));

        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        addComponents(chart, unselectAllButton);
        setExpandRatio(chart, 1);
        setExpandRatio(unselectAllButton, 0);
    }

    private void unselectAllSeries() {
        selectedSeriesIds.clear();
        setSeriesSelection(false);
    }

    private void setSeriesSelection(boolean visible) {
        List<Series> seriesList = chart.getConfiguration().getSeries();
        for (Series series : seriesList) {
            AbstractSeries dataSeries = (AbstractSeries) series;
            dataSeries.setVisible(visible);
        }
        chart.drawChart();
    }

    void setSeries(List<SerieViewModel> serieViewModels) {
        setSelectedSeries(serieViewModels);
        Configuration configuration = chart.getConfiguration();
        for (SerieViewModel serieViewModel : serieViewModels) {
            DataSeries series = buildDataSeries(serieViewModel);
            configuration.addSeries(series);
        }
    }

    Collection<Integer> getSelectedSeriesIds() {
        return selectedSeriesIds;
    }

    private void setSelectedSeries(List<SerieViewModel> serieViewModels) {
        selectedSeriesIds.clear();
        serieViewModels.stream()
                       .map(SerieViewModel::getId)
                       .forEach(selectedSeriesIds::add);
    }

    private void onSeriesShown(Series series) {
        selectedSeriesIds.add(getId(series));
    }

    private void onSeriesHiden(Series series) {
        selectedSeriesIds.remove(getId(series));
    }

    private static int getId(Series series) {
        String id = series.getId();
        return Integer.valueOf(id);
    }

    private static DataSeries buildDataSeries(SerieViewModel serieViewModel) {
        String name = serieViewModel.toString();
        DataSeries series = new DataSeries(name);
        int id = serieViewModel.getId();
        String idString = Integer.toString(id);
        series.setId(idString);
        RawPoints rawPoints = serieViewModel.getRawPoints();
        for (RawPoint rawPoint : rawPoints) {
            DataSeriesItem item = buildDataSeriesItem(rawPoint);
            series.add(item);
        }
        return series;
    }

    private static DataSeriesItem buildDataSeriesItem(RawPoint rawPoint) {
        float x = rawPoint.getX();
        float y = rawPoint.getY();
        return new DataSeriesItem(x, y);
    }
}
