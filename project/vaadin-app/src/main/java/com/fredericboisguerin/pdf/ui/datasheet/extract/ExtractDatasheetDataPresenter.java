package com.fredericboisguerin.pdf.ui.datasheet.extract;

import com.fredericboisguerin.pdf.actions.ViewDatasheetGraphPDFFile;
import com.fredericboisguerin.pdf.actions.ViewDatasheetMetaInfo;
import com.fredericboisguerin.pdf.actions.export.ExportDataExcel;
import com.fredericboisguerin.pdf.actions.export.ExportGraphData;
import com.fredericboisguerin.pdf.actions.extract.ExtractGraphFromPDFFile;
import com.fredericboisguerin.pdf.graph.*;
import com.fredericboisguerin.pdf.model.AxisName;
import com.fredericboisguerin.pdf.model.DatasheetGraphExtraInfo;
import com.fredericboisguerin.pdf.model.datasheet.*;
import com.fredericboisguerin.pdf.model.datasheet.pdf.PDFFile;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;

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
        DatasheetMetaInfo datasheetMetaInfo = getDatasheetMetaInfo(datasheetId);
        DatasheetGraph datasheetGraph = getDatasheetGraph(datasheetId, graphId);
        setTitleToView(datasheetMetaInfo);
        setSeriesToView(datasheetGraph.getPDFFile());
        setAxesViewModelToView();
        seExportResourceToView(datasheetMetaInfo, datasheetGraph);
    }

    private void seExportResourceToView(DatasheetMetaInfo datasheetMetaInfo, DatasheetGraph datasheetGraph) {
        DatasheetGraphExtraInfo datasheetGraphExtraInfo = datasheetGraph.getDatasheetGraphExtraInfo();
        Resource resource = new StreamResource(this::getExportInputStream, getFilename(datasheetMetaInfo, datasheetGraphExtraInfo));
        view.setExportResource(resource);
    }

    private DatasheetMetaInfo getDatasheetMetaInfo(String datasheetId) {
        ViewDatasheetMetaInfo viewDatasheetMetaInfo = new ViewDatasheetMetaInfo(datasheetId);
        return viewDatasheetMetaInfo.execute(datasheetService);
    }

    private String getFilename(DatasheetMetaInfo datasheetMetaInfo, DatasheetGraphExtraInfo datasheetGraphExtraInfo) {
        DatasheetReference reference = datasheetMetaInfo.getReference();
        DatasheetSupplier supplier = datasheetMetaInfo.getSupplier();
        AxisName xAxisName = datasheetGraphExtraInfo.getxAxisName();
        AxisName yAxisName = datasheetGraphExtraInfo.getyAxisName();
        return String.format("%s_%s_%s_%s.xls", reference, supplier, yAxisName, xAxisName);
    }

    private void setTitleToView(DatasheetMetaInfo datasheetMetaInfo) {
        view.setDatasheetInfo(datasheetMetaInfo.toString());
    }

    private void setSeriesToView(PDFFile pdfFile) {
        ExtractGraphFromPDFFile extractGraphFromPDFFile = new ExtractGraphFromPDFFile(pdfFile);
        try {
            this.xyGraph = extractGraphFromPDFFile.execute();
            List<SerieViewModel> serieViewModels = getSerieViewModels(xyGraph);
            view.setSeries(serieViewModels);
        } catch (Exception e) {
            view.notifyError("Error during extracting data from datasheet");
        }
    }

    private void setAxesViewModelToView() {
        this.axesViewModel = new AxesViewModel(buildAxisViewModel(), buildAxisViewModel());
        view.setAxesViewModel(axesViewModel);
    }

    private DatasheetGraph getDatasheetGraph(String datasheetId, String graphId) {
        ViewDatasheetGraphPDFFile viewDatasheetGraphPDFFile = new ViewDatasheetGraphPDFFile(
                datasheetId, graphId);
        return viewDatasheetGraphPDFFile.execute(datasheetService);
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

    private InputStream getExportInputStream() {
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
