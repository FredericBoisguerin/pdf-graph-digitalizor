package com.fredericboisguerin.pdf.ui.graph.list;

import com.fredericboisguerin.pdf.actions.ViewDatasheetGraphs;
import com.fredericboisguerin.pdf.actions.ViewDatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetGraph;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReadDatasheetGraphPresenter implements ReadDatasheetGraphViewListener {
    private final DatasheetService datasheetService;
    private final ReadDatasheetGraphView view;

    private String datasheetId;

    public ReadDatasheetGraphPresenter(ReadDatasheetGraphView vaadinReadView,
                                       DatasheetService datasheetService) {
        view = vaadinReadView;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onViewEntered(String parameter) {
        this.datasheetId = parameter;
        setTitleToView();
        setDatasheetsToView(parameter);
    }

    private void setTitleToView() {
        ViewDatasheetMetaInfo viewDatasheetMetaInfo = new ViewDatasheetMetaInfo(datasheetId);
        DatasheetMetaInfo datasheetMetaInfo = viewDatasheetMetaInfo.execute(datasheetService);
        view.setDatasheetInfo(datasheetMetaInfo.toString());
    }

    private void setDatasheetsToView(String parameter) {
        ViewDatasheetGraphs viewDatasheetGraphs = new ViewDatasheetGraphs(parameter);
        Collection<DatasheetGraph> datasheets = viewDatasheetGraphs.execute(datasheetService);
        List<DatasheetGraphViewModel> datasheetGraphViewModels = datasheets.stream()
                                                                           .map(this::buildDatasheetGraphViewModel)
                                                                           .collect(
                                                                                   Collectors
                                                                                           .toList());
        view.setDatasheets(datasheetGraphViewModels);
    }

    @Override
    public void onDatasheetSelectedForExtraction(
            Optional<DatasheetGraphViewModel> datasheetGraphViewModel) {
        if (!datasheetGraphViewModel.isPresent()) {
            view.notifyTray("Please select a datasheet graph to extract data from!");
            return;
        }
        String datasheetGraphId = datasheetGraphViewModel.map(DatasheetGraphViewModel::getId)
                                                         .get();
        view.navigateToExtractDatasheetData(datasheetId, datasheetGraphId);
    }

    private DatasheetGraphViewModel buildDatasheetGraphViewModel(DatasheetGraph datasheetGraph) {
        String id = datasheetGraph.getId();
        String filename = datasheetGraph.getFilename();
        return new DatasheetGraphViewModel(id, filename);
    }
}
