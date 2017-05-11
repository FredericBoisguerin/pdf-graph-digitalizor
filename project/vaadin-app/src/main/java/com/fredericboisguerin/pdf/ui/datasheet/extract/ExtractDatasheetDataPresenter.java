package com.fredericboisguerin.pdf.ui.datasheet.extract;

import com.fredericboisguerin.pdf.actions.extract.ExtractGraphFromPDFFile;
import com.fredericboisguerin.pdf.graph.XYGraph;
import com.fredericboisguerin.pdf.graph.XYPointSeries;
import com.fredericboisguerin.pdf.model.datasheet.Datasheet;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

import java.util.List;
import java.util.stream.Collectors;

public class ExtractDatasheetDataPresenter implements ExtractDatasheetDataViewListener {

    private final ExtractDatasheetDataView view;
    private final DatasheetService datasheetService;
    private int currentSerieId = 0;

    public ExtractDatasheetDataPresenter(ExtractDatasheetDataView view, DatasheetService datasheetService) {
        this.view = view;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onViewEntered(String parameter) {
        Datasheet datasheet = datasheetService.findById(parameter);
        view.setDatasheetInfo(datasheet.toString());
        ExtractGraphFromPDFFile extractGraphFromPDFFile = new ExtractGraphFromPDFFile(datasheet
                .getPDFFile());
        try {
            XYGraph xyGraph = extractGraphFromPDFFile.execute();
            List<SerieViewModel> serieViewModels = xyGraph.stream()
                                                          .map(this::buildSerie)
                                                          .collect(Collectors
                                                                  .toList());
            view.setSeries(serieViewModels);
        } catch (Exception e) {
            view.notifyError("Error during extracting data from datasheet");
        }
    }

    private SerieViewModel buildSerie(XYPointSeries xyPoints) {
        currentSerieId++;
        return new SerieViewModel("Serie n°" + currentSerieId, xyPoints.size());
    }
}
