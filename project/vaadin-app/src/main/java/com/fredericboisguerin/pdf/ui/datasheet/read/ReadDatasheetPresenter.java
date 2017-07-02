package com.fredericboisguerin.pdf.ui.datasheet.read;

import com.fredericboisguerin.pdf.actions.ArchiveDatasheet;
import com.fredericboisguerin.pdf.actions.ViewAllDatasheets;
import com.fredericboisguerin.pdf.model.datasheet.Datasheet;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetCollection;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

import java.util.List;
import java.util.stream.Collectors;

public class ReadDatasheetPresenter implements ReadDatasheetViewListener {
    private final DatasheetService datasheetService;
    private final ReadDatasheetView view;

    public ReadDatasheetPresenter(ReadDatasheetView vaadinReadView,
            DatasheetService datasheetService) {
        view = vaadinReadView;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onViewEntered() {
        setDatasheetsInView();
    }

    private void setDatasheetsInView() {
        DatasheetCollection datasheets = new ViewAllDatasheets().execute(datasheetService);
        List<DatasheetViewModel> datasheetViewModels = datasheets.stream()
                                                                 .map(this::buildDatasheetViewModel)
                                                                 .collect(Collectors.toList());
        view.setDatasheets(datasheetViewModels);
    }

    @Override
    public void onDatasheetSelectedForViewGraphs(DatasheetViewModel datasheetViewModel) {
        String id = datasheetViewModel.getId();
        view.navigateToViewDatasheetGraphs(id);
    }

    @Override
    public void onDatasheetSelectedForAddGraph(DatasheetViewModel datasheetViewModel) {
        String id = datasheetViewModel.getId();
        view.navigateToAddDatasheetGraph(id);
    }

    @Override
    public void onCreateDatasheetButtonClicked() {
        view.navigateToCreateDatasheet();
    }

    @Override
    public void onDatasheetSelectedForEditNameAndSuppplier(DatasheetViewModel datasheetViewModel) {
        view.navigateToEditDatasheet(datasheetViewModel.getId());
    }

    @Override
    public void onDatasheetSelectedForArchive(DatasheetViewModel datasheetViewModel) {
        String datasheetId = datasheetViewModel.getId();
        ArchiveDatasheet archiveDatasheet = new ArchiveDatasheet(datasheetId);
        archiveDatasheet.execute(datasheetService);
        setDatasheetsInView();
    }

    private DatasheetViewModel buildDatasheetViewModel(Datasheet datasheet) {
        DatasheetMetaInfo datasheetMetaInfo = datasheet.getDatasheetMetaInfo();
        String reference = datasheetMetaInfo.getReference().toString();
        String supplier = datasheetMetaInfo.getSupplier().toString();
        return new DatasheetViewModel(datasheet.getId(), reference, supplier);
    }
}
