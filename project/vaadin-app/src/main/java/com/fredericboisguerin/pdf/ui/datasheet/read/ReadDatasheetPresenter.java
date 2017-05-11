package com.fredericboisguerin.pdf.ui.datasheet.read;

import com.fredericboisguerin.pdf.model.datasheet.Datasheet;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ReadDatasheetPresenter implements ReadDatasheetViewListener {
    private final DatasheetService datasheetService;
    private final ReadDatasheetView view;

    public ReadDatasheetPresenter(ReadDatasheetView vaadinReadView, DatasheetService datasheetService) {
        view = vaadinReadView;
        this.datasheetService = datasheetService;
    }

    @Override
    public void onViewEntered() {
        Collection<Datasheet> datasheets = datasheetService.getAllDatasheets();
        List<DatasheetViewModel> datasheetViewModels = datasheets.stream()
                                                                 .map(this::buildDatasheetViewModel)
                                                                 .collect(Collectors
                                                                         .toList());
        view.setDatasheets(datasheetViewModels);
    }

    private DatasheetViewModel buildDatasheetViewModel(Datasheet datasheet) {
        String reference = datasheet.getDatasheetReference().toString();
        String supplier = datasheet.getSupplier().toString();
        return new DatasheetViewModel(reference, supplier);
    }
}
