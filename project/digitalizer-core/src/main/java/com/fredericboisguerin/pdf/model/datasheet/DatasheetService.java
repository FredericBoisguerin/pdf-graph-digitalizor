package com.fredericboisguerin.pdf.model.datasheet;

import java.util.Collection;
import java.util.UUID;

import com.fredericboisguerin.pdf.infrastructure.DatasheetRepository;

public class DatasheetService {

    private final DatasheetRepository datasheetRepository;

    public DatasheetService(DatasheetRepository datasheetRepository) {
        this.datasheetRepository = datasheetRepository;
    }

    public UUID importDatasheet(DatasheetReference datasheetReference, DatasheetSupplier supplier) {
        Datasheet datasheet = new Datasheet(datasheetReference, supplier);
        datasheetRepository.save(datasheet);
        return datasheet.getUUID();
    }

    public Collection<Datasheet> getAllDatasheets() {
        return datasheetRepository.findAll();
    }

    public Datasheet findById(String parameter) {
        return datasheetRepository.findById(parameter).orElseThrow(IllegalAccessError::new);
    }

    public void addGraphFromPDF(String datasheetId, DatasheetGraph datasheetGraph) {
        Datasheet datasheet = findById(datasheetId);
        datasheet.addGraph(datasheetGraph);
        datasheetRepository.save(datasheet);
    }
}
