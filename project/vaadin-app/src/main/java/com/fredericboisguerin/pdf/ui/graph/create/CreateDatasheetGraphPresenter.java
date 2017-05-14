package com.fredericboisguerin.pdf.ui.graph.create;

import com.fredericboisguerin.pdf.actions.AddGraphToDatasheet;
import com.fredericboisguerin.pdf.model.AxisName;
import com.fredericboisguerin.pdf.model.DatasheetGraphExtraInfo;
import com.fredericboisguerin.pdf.model.PhysicalParameter;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.PDFFile;

public class CreateDatasheetGraphPresenter implements CreateDatasheetGraphListener {
    private final CreateDatasheetGraphView view;
    private final DatasheetService datasheetService;

    private String datasheetId;
    private CreateDatasheetGraphViewModel model;

    public CreateDatasheetGraphPresenter(CreateDatasheetGraphView view,
            DatasheetService datasheetService) {
        this.view = view;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onViewEntered(String datasheetId) {
        this.datasheetId = datasheetId;
        this.model = new CreateDatasheetGraphViewModel();
        String datasheetInfo = datasheetService.getDatasheetInfo(datasheetId);
        view.setDatasheetInfo(datasheetInfo);
        view.setModel(model);
    }

    @Override
    public void onValidateButtonClicked(String filename, byte[] bytes) {
        PDFFile pdfFile = new PDFFile(filename, bytes);
        DatasheetGraphExtraInfo datasheetGraphExtraInfo = buildDatasheetGraphExtraInfo();
        AddGraphToDatasheet addGraphToDatasheet = new AddGraphToDatasheet(datasheetId,
                datasheetGraphExtraInfo, pdfFile);
        addGraphToDatasheet.execute(datasheetService);
        view.notifyMessage("Graph created!");
        view.navigateToReadDatasheet(datasheetId);
    }

    private DatasheetGraphExtraInfo buildDatasheetGraphExtraInfo() {
        AxisName xAxisName = buildAxisName(model.getxAxis());
        AxisName yAxisName = buildAxisName(model.getyAxis());
        PhysicalParameter physicalParameter = buildPhysicalParameter(model.getParameter());
        return new DatasheetGraphExtraInfo(xAxisName, yAxisName, physicalParameter);
    }

    private PhysicalParameter buildPhysicalParameter(ParameterViewModel parameter) {
        return new PhysicalParameter(parameter.getName());
    }

    private AxisName buildAxisName(AxisViewModel axisViewModel) {
        return new AxisName(axisViewModel.getName());
    }
}
