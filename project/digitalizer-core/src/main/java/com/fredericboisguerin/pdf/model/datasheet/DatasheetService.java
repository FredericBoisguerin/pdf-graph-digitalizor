package com.fredericboisguerin.pdf.model.datasheet;

import java.util.Collection;

import com.fredericboisguerin.pdf.infrastructure.DatasheetRepository;

public class DatasheetService {

    private final DatasheetRepository datasheetRepository;

    public DatasheetService(DatasheetRepository datasheetRepository) {
        this.datasheetRepository = datasheetRepository;
    }

    public void importDatasheet(DatasheetReference datasheetReference, DatasheetSupplier supplier,
            PDFFile pdfFile) {
        datasheetRepository.create(new Datasheet(datasheetReference, supplier, pdfFile));
    }

    public Collection<Datasheet> getAllDatasheets() {
        return datasheetRepository.findAll();
    }
}
