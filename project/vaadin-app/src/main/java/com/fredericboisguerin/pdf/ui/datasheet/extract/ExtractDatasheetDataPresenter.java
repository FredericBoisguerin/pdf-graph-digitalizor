package com.fredericboisguerin.pdf.ui.datasheet.extract;

import com.fredericboisguerin.pdf.actions.ViewDatasheetGraphPDFFile;
import com.fredericboisguerin.pdf.actions.ViewDatasheetMetaInfo;
import com.fredericboisguerin.pdf.actions.export.ExportDataExcel;
import com.fredericboisguerin.pdf.actions.export.ExportGraphData;
import com.fredericboisguerin.pdf.actions.extract.ExtractGraphFromPDFFile;
import com.fredericboisguerin.pdf.graph.*;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class ExtractDatasheetDataPresenter implements ExtractDatasheetDataViewListener {

    private static final AxisKind LINEAR = new AxisKind("Linear");
    private static final AxisKind LOG = new AxisKind("Log");
    private static final Collection<AxisKind> AXIS_KINDS = Arrays.asList(LINEAR, LOG);

    private final ExtractDatasheetDataView view;
    private final DatasheetService datasheetService;

    private AxesViewModel axesViewModel;
    private XYGraph xyGraph;
    private String datasheetId;
    private String graphId;

    public ExtractDatasheetDataPresenter(ExtractDatasheetDataView view,
                                         DatasheetService datasheetService) {
        this.view = view;
        this.datasheetService = datasheetService;
    }

    private static AxisViewModel buildAxisViewModel() {
        return new AxisViewModel(AXIS_KINDS);
    }

    @Override
    public void onViewEntered(String datasheetId, String graphId) {
        this.datasheetId = datasheetId;
        this.graphId = graphId;
        this.axesViewModel = new AxesViewModel(buildAxisViewModel(), buildAxisViewModel());
        PDFFile pdfFile = getPdfFile(datasheetId, graphId);
        ExtractGraphFromPDFFile extractGraphFromPDFFile = new ExtractGraphFromPDFFile(pdfFile);
        try {
            xyGraph = extractGraphFromPDFFile.execute();
            setSeriesAndModelToView();
        } catch (Exception e) {
            view.notifyError("Error during extracting data from datasheet");
        }
    }

    private void setSeriesAndModelToView() {
        setTitleToView(datasheetId);
        List<SerieViewModel> serieViewModels = getSerieViewModels(xyGraph);
        view.setSeries(serieViewModels);
        view.setAxesViewModel(axesViewModel);
    }

    private PDFFile getPdfFile(String datasheetId, String graphId) {
        ViewDatasheetGraphPDFFile viewDatasheetGraphPDFFile = new ViewDatasheetGraphPDFFile(
                datasheetId, graphId);
        return viewDatasheetGraphPDFFile.execute(datasheetService);
    }

    private void setTitleToView(String datasheetId) {
        ViewDatasheetMetaInfo viewDatasheetMetaInfo = new ViewDatasheetMetaInfo(datasheetId);
        DatasheetMetaInfo datasheetMetaInfo = viewDatasheetMetaInfo.execute(datasheetService);
        view.setDatasheetInfo(datasheetMetaInfo.toString());
    }

    private List<SerieViewModel> getSerieViewModels(XYGraph xyGraph) {
        int displayedId = 1;
        List<SerieViewModel> serieViewModels = new ArrayList<>();
        RawPointsBuilder rawPointsBuilder = new RawPointsBuilder();
        for (Serie serie : xyGraph.getSeriesBySizeDesc()) {
            RawPoints rawPoints = rawPointsBuilder.buildRawPoints(serie);
            SerieViewModel serieViewModel = new SerieViewModel(serie.getUuid(), displayedId,
                    rawPoints);
            serieViewModels.add(serieViewModel);
            displayedId++;
        }
        return serieViewModels;
    }

    @Override
    public InputStream getExportInputStream() {
        Collection<UUID> selectedSeriesIds = view.getSelectedSeriesIds();
        Axis xAxis = toAxis(axesViewModel.getAxisX());
        Axis yAxis = toAxis(axesViewModel.getAxisY());
        ExportGraphData exportGraphData = new ExportGraphData(xyGraph, xAxis,
                yAxis, selectedSeriesIds);
        ExportDataExcel exportDataExcel = new ExportDataExcel(datasheetId,
                graphId, exportGraphData);
        try {
            File execute = exportDataExcel.execute(datasheetService);
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
