package com.fredericboisguerin.pdf.model.datasheet;

import java.util.Collection;
import java.util.UUID;

import com.fredericboisguerin.pdf.infrastructure.DatasheetRepository;

public class DatasheetService {

    private final DatasheetRepository datasheetRepository;

    public DatasheetService(DatasheetRepository datasheetRepository) {
        this.datasheetRepository = datasheetRepository;
    }

    public UUID createDatasheet(DatasheetReference datasheetReference, DatasheetSupplier supplier) {
        Datasheet datasheet = new Datasheet(datasheetReference, supplier);
        datasheetRepository.save(datasheet);
        return datasheet.getUUID();
    }

    public Collection<Datasheet> getAllDatasheets() {
        return datasheetRepository.findAll();
    }

    public Datasheet findById(String datasheetId) {
        return datasheetRepository.findById(datasheetId).orElseThrow(IllegalAccessError::new);
    }

    public void addGraphFromPDF(String datasheetId, DatasheetGraph datasheetGraph) {
        Datasheet datasheet = findById(datasheetId);
        datasheet.addGraph(datasheetGraph);
        datasheetRepository.save(datasheet);
    }

    public Collection<DatasheetGraph> getAllDatasheetGraphs(String datasheetId) {
        Datasheet datasheet = findById(datasheetId);
        return datasheet.getDatasheetGraphs();
    }

    public PDFFile getPDFFile(String datasheetId, String graphId) {
        return findById(datasheetId).getDatasheetGraphs(graphId)
                                    .map(DatasheetGraph::getPDFFile)
                                    .orElseThrow(IllegalStateException::new);
    }

    public String getDatasheetInfo(String datasheetId) {
        return findById(datasheetId).toString();
    }
}
