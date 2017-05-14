package com.fredericboisguerin.pdf.ui.datasheet.read;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fredericboisguerin.pdf.actions.ViewAllDatasheets;
import com.fredericboisguerin.pdf.model.datasheet.Datasheet;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetCollection;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

public class ReadDatasheetPresenter implements ReadDatasheetViewListener {
    private final DatasheetService datasheetService;
    private final ReadDatasheetView view;

    public ReadDatasheetPresenter(ReadDatasheetView vaadinReadView, DatasheetService datasheetService) {
        view = vaadinReadView;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onViewEntered() {
        DatasheetCollection datasheets = new ViewAllDatasheets().execute(datasheetService);
        List<DatasheetViewModel> datasheetViewModels = datasheets.stream()
                                                                 .map(this::buildDatasheetViewModel)
                                                                 .collect(Collectors
                                                                         .toList());
        view.setDatasheets(datasheetViewModels);
    }

    @Override
    public void onDatasheetSelectedForViewGraphs(Optional<DatasheetViewModel> datasheetViewModel) {
        if (!datasheetViewModel.isPresent()){
            view.notifyTray("Please select a datasheet!");
            return;
        }
        String id = datasheetViewModel.map(DatasheetViewModel::getId).get();
        view.navigateToViewDatasheetGraphs(id);
    }

    private DatasheetViewModel buildDatasheetViewModel(Datasheet datasheet) {
        String reference = datasheet.getDatasheetReference().toString();
        String supplier = datasheet.getSupplier().toString();
        return new DatasheetViewModel(datasheet.getId(), reference, supplier);
    }
}
