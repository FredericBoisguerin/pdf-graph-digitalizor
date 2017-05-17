package com.fredericboisguerin.pdf.ui.datasheet.read;

import java.util.List;
import java.util.stream.Collectors;

import com.fredericboisguerin.pdf.actions.ViewAllDatasheets;
import com.fredericboisguerin.pdf.model.datasheet.Datasheet;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetCollection;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

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

    private DatasheetViewModel buildDatasheetViewModel(Datasheet datasheet) {
        DatasheetMetaInfo datasheetMetaInfo = datasheet.getDatasheetMetaInfo();
        String reference = datasheetMetaInfo.getReference().toString();
        String supplier = datasheetMetaInfo.getSupplier().toString();
        return new DatasheetViewModel(datasheet.getId(), reference, supplier);
    }
}
