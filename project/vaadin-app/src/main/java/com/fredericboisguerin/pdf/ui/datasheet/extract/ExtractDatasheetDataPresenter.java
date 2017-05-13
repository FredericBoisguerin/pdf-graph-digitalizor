package com.fredericboisguerin.pdf.ui.datasheet.extract;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fredericboisguerin.pdf.actions.ExportDataExcel;
import com.fredericboisguerin.pdf.actions.extract.ExtractGraphFromPDFFile;
import com.fredericboisguerin.pdf.graph.Axis;
import com.fredericboisguerin.pdf.graph.AxisCoords;
import com.fredericboisguerin.pdf.graph.Coord;
import com.fredericboisguerin.pdf.graph.LinearAxis;
import com.fredericboisguerin.pdf.graph.LogAxis;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.graph.Serie;
import com.fredericboisguerin.pdf.model.datasheet.Datasheet;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class ExtractDatasheetDataPresenter implements ExtractDatasheetDataViewListener {

    private static final AxisKind LINEAR = new AxisKind("Linear");
    private static final AxisKind LOG = new AxisKind("Log");
    private static final Collection<AxisKind> AXIS_KINDS = Arrays.asList(LINEAR, LOG);

    private final ExtractDatasheetDataView view;
    private final DatasheetService datasheetService;

    private AxesViewModel axesViewModel;
    private Map<SerieViewModel, Serie> pointSeriesMap;
    private XYGraph xyGraph;

    public ExtractDatasheetDataPresenter(ExtractDatasheetDataView view,
            DatasheetService datasheetService) {
        this.view = view;
        this.datasheetService = datasheetService;
    }

    private static AxisViewModel buildAxisViewModel() {
        return new AxisViewModel(AXIS_KINDS);
    }

    @Override
    public void onViewEntered(String parameter) {
        this.axesViewModel = new AxesViewModel(buildAxisViewModel(), buildAxisViewModel());
        Datasheet datasheet = datasheetService.findById(parameter);
        view.setDatasheetInfo(datasheet.toString());
        view.setAxesViewModels(axesViewModel);
        ExtractGraphFromPDFFile extractGraphFromPDFFile = new ExtractGraphFromPDFFile(
                datasheet.getPDFFile());
        try {
            xyGraph = extractGraphFromPDFFile.execute();
            List<SerieViewModel> serieViewModels = getSerieViewModels(xyGraph);
            view.setSeries(serieViewModels);
        } catch (Exception e) {
            view.notifyError("Error during extracting data from datasheet");
        }
    }

    private List<SerieViewModel> getSerieViewModels(XYGraph xyGraph) {
        int currentSerieId = 1;
        pointSeriesMap = new HashMap<>();
        List<SerieViewModel> serieViewModels = new ArrayList<>();
        for (Serie serie : xyGraph.getSeriesBySizeDesc()) {
            SerieViewModel serieViewModel = new SerieViewModel(currentSerieId,
                    "Serie n°" + currentSerieId, serie.size());
            serieViewModels.add(serieViewModel);
            pointSeriesMap.put(serieViewModel, serie);
            currentSerieId++;
        }
        return serieViewModels;
    }

    @Override
    public InputStream getExportInputStream() {
        Collection<SerieViewModel> selectedSeriesModel = view.getSelectedSeries();
        Axis xAxis = toAxis(axesViewModel.getAxisX());
        Axis yAxis = toAxis(axesViewModel.getAxisY());
        List<Serie> selectedSeries = selectedSeriesModel.stream()
                                                        .map(pointSeriesMap::get)
                                                        .collect(Collectors.toList());
        ExportDataExcel exportDataExcel = new ExportDataExcel(xyGraph, xAxis, yAxis,
                selectedSeries);
        try {
            File execute = exportDataExcel.execute();
            return new FileInputStream(execute);
        } catch (Exception e) {
            view.notifyError("An error has occured while exporting file");
        }
        return null;
    }

    private Axis toAxis(AxisViewModel axisViewModel) {
        AxisKind selectedAxisKind = axisViewModel.getSelectedAxisKind();
        float min = axisViewModel.getMin();
        float max = axisViewModel.getMax();
        AxisCoords axisCoords = new AxisCoords(buildCoord(min), buildCoord(max));
        if (selectedAxisKind.equals(LINEAR)) {
            return new LinearAxis(axisCoords);
        }
        if (selectedAxisKind.equals(LOG)) {
            return new LogAxis(axisCoords);
        }
        throw new IllegalArgumentException("Axis kind " + selectedAxisKind + " not supported.");
    }

    private Coord buildCoord(float max) {
        return Coord.of(max);
    }
}
