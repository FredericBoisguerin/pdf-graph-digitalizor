package com.fredericboisguerin.pdf.ui.graph.create;

import java.io.IOException;

import com.fredericboisguerin.pdf.actions.AddGraphToDatasheet;
import com.fredericboisguerin.pdf.actions.ViewDatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.AxisName;
import com.fredericboisguerin.pdf.model.DatasheetGraphExtraInfo;
import com.fredericboisguerin.pdf.model.PhysicalParameter;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.pdf.ImageCrop;
import com.fredericboisguerin.pdf.model.datasheet.pdf.PDFFile;
import com.fredericboisguerin.pdf.model.datasheet.pdf.PDFImage;

public class CreateDatasheetGraphPresenter implements CreateDatasheetGraphListener {
    private static final String PNG_FORMAT_NAME = "png";
    private final CreateDatasheetGraphView view;
    private final DatasheetService datasheetService;

    private String datasheetId;
    private PDFFile pdfFile;
    private boolean cropDone;
    private CreateDatasheetGraphViewModel model;

    public CreateDatasheetGraphPresenter(CreateDatasheetGraphView view,
            DatasheetService datasheetService) {
        this.view = view;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onViewEntered(String datasheetId) {
        this.datasheetId = datasheetId;
        setTitleToView(datasheetId);
        this.model = new CreateDatasheetGraphViewModel();
        cropDone = false;
        view.setModel(model);
    }

    private void setTitleToView(String datasheetId) {
        ViewDatasheetMetaInfo viewDatasheetMetaInfo = new ViewDatasheetMetaInfo(datasheetId);
        DatasheetMetaInfo datasheetMetaInfo = viewDatasheetMetaInfo.execute(datasheetService);
        view.setDatasheetInfo(datasheetMetaInfo.toString());
    }

    @Override
    public void onValidateButtonClicked() {
        if (!cropDone){
            view.displayPleaseCropFirst();
            return;
        }
        DatasheetGraphExtraInfo datasheetGraphExtraInfo = buildDatasheetGraphExtraInfo();
        AddGraphToDatasheet addGraphToDatasheet = new AddGraphToDatasheet(datasheetId,
                datasheetGraphExtraInfo, pdfFile);
        addGraphToDatasheet.execute(datasheetService);
        view.notifyMessage("Graph created!");
        view.navigateToReadDatasheet(datasheetId);
    }

    @Override
    public void onFileUpdated(String filename, byte[] bytes) {
        this.pdfFile = new PDFFile(filename, bytes);
        try {
            PDFImage inputStream = pdfFile.asPDFImage(PNG_FORMAT_NAME);
            view.setImageToCrop(inputStream);
        } catch (IOException e) {
            view.displayErrorImpossibleToCropFile();
        }
    }

    @Override
    public void onCropReset() {
        pdfFile.resetCrop();
        cropDone = false;
    }

    @Override
    public void onCropSelection(ImageCrop imageCrop) {
        pdfFile.setCrop(imageCrop);
        cropDone = true;
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
