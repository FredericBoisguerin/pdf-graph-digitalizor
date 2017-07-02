package com.fredericboisguerin.pdf.ui.graph.list;

import com.fredericboisguerin.pdf.actions.RemoveDatasheetGraph;
import com.fredericboisguerin.pdf.actions.ViewDatasheetGraphs;
import com.fredericboisguerin.pdf.actions.ViewDatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.AxisName;
import com.fredericboisguerin.pdf.model.DatasheetGraphExtraInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetGraph;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

import java.util.Collection;
import java.util.List;
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
        giveGraphsToView();
    }

    private void setTitleToView() {
        ViewDatasheetMetaInfo viewDatasheetMetaInfo = new ViewDatasheetMetaInfo(datasheetId);
        DatasheetMetaInfo datasheetMetaInfo = viewDatasheetMetaInfo.execute(datasheetService);
        view.setDatasheetInfo(datasheetMetaInfo.toString());
    }

    private void giveGraphsToView() {
        ViewDatasheetGraphs datasheetGraphs = new ViewDatasheetGraphs(datasheetId);
        Collection<DatasheetGraph> graphs = datasheetGraphs.execute(datasheetService);
        List<DatasheetGraphViewModel> datasheetGraphViewModels = graphs.stream()
                                                                       .map(this::buildDatasheetGraphViewModel)
                                                                       .collect(Collectors.toList());
        view.setDatasheets(datasheetGraphViewModels);
    }

    @Override
    public void onDatasheetSelectedForExtraction(
            DatasheetGraphViewModel datasheetGraphViewModel) {
        String datasheetGraphId = datasheetGraphViewModel.getId();
        view.navigateToExtractDatasheetData(datasheetId, datasheetGraphId);
    }

    @Override
    public void onGraphSelectedForRemoval(DatasheetGraphViewModel datasheetGraphViewModel) {
        RemoveDatasheetGraph removeDatasheetGraph = new RemoveDatasheetGraph(datasheetId, datasheetGraphViewModel.getId());
        removeDatasheetGraph.execute(datasheetService);
        giveGraphsToView();
    }

    @Override
    public void onNewGraph() {
        view.navigateToNewGraph(datasheetId);
    }

    private DatasheetGraphViewModel buildDatasheetGraphViewModel(DatasheetGraph datasheetGraph) {
        String id = datasheetGraph.getId();
        String filename = datasheetGraph.getFilename();
        DatasheetGraphExtraInfo datasheetGraphExtraInfo = datasheetGraph.getDatasheetGraphExtraInfo();
        AxisName yAxisName = datasheetGraphExtraInfo.getyAxisName();
        AxisName xAxisName = datasheetGraphExtraInfo.getxAxisName();
        return new DatasheetGraphViewModel(id, yAxisName, xAxisName, filename);
    }
}
