package com.fredericboisguerin.pdf.ui.graph.create;

import java.io.IOException;

import com.fredericboisguerin.pdf.actions.AddGraphToDatasheet;
import com.fredericboisguerin.pdf.actions.ViewDatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.AxisName;
import com.fredericboisguerin.pdf.model.DatasheetGraphExtraInfo;
import com.fredericboisguerin.pdf.model.PhysicalParameter;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import com.fredericboisguerin.pdf.model.datasheet.pdf.*;
import com.fredericboisguerin.pdf.ui.graph.create.model.graphinfo.AxisViewModel;
import com.fredericboisguerin.pdf.ui.graph.create.model.graphinfo.DatasheetGraphInfoViewModel;
import com.fredericboisguerin.pdf.ui.graph.create.model.graphinfo.ParameterViewModel;

public class CreateDatasheetGraphPresenter implements CreateDatasheetGraphListener {
    private static final String PNG_FORMAT_NAME = "png";
    private final CreateDatasheetGraphView view;
    private final DatasheetService datasheetService;

    private String datasheetId;
    private DatasheetGraphInfoViewModel model;
    private PDFFile pdfFile;
    private ImageCrop imageCrop = ImageCrop.noCrop();
    private int selectedPageIndex;

    public CreateDatasheetGraphPresenter(CreateDatasheetGraphView view,
                                         DatasheetService datasheetService) {
        this.view = view;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onViewEntered(String datasheetId) {
        this.datasheetId = datasheetId;
        setTitleToView(datasheetId);
        this.model = new DatasheetGraphInfoViewModel();
        view.setModel(model);
    }

    private void setTitleToView(String datasheetId) {
        ViewDatasheetMetaInfo viewDatasheetMetaInfo = new ViewDatasheetMetaInfo(datasheetId);
        DatasheetMetaInfo datasheetMetaInfo = viewDatasheetMetaInfo.execute(datasheetService);
        view.setDatasheetInfo(datasheetMetaInfo.toString());
    }

    @Override
    public void onValidateButtonClicked() {
        DatasheetGraphExtraInfo datasheetGraphExtraInfo = buildDatasheetGraphExtraInfo();
        PDFPage pdfPage = pdfFile.getPage(selectedPageIndex);
        AddGraphToDatasheet addGraphToDatasheet = new AddGraphToDatasheet(datasheetId,
                datasheetGraphExtraInfo, new DatasheetGraphPDF(pdfPage, imageCrop));
        addGraphToDatasheet.execute(datasheetService);
        view.notifyMessage("Graph created!");
        view.navigateToReadDatasheet(datasheetId);
    }

    @Override
    public void onFileUpdated(String filename, byte[] bytes) {
        pdfFile = new PDFFile(filename, bytes);
        try {
            selectedPageIndex = 0;
            PDFImage inputStream = pdfFile.getPage(selectedPageIndex).asPDFImage(PNG_FORMAT_NAME);
            view.setImageToCrop(inputStream);
        } catch (IOException e) {
            view.displayErrorImpossibleToCropFile();
        }
    }

    @Override
    public void onCropReset() {
        imageCrop = ImageCrop.noCrop();
    }

    @Override
    public void onCropSelection(ImageCrop imageCrop) {
        this.imageCrop = imageCrop;
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
