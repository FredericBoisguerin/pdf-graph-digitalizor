package com.fredericboisguerin.pdf.model.datasheet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class DatasheetCollection {

    private final List<Datasheet> datasheetList = new ArrayList<>();

    public DatasheetCollection(Collection<Datasheet> datasheets) {
        this.datasheetList.addAll(datasheets);
        datasheetList.sort(Comparator.comparing(Datasheet::getDatasheetReference));
    }

    public List<Datasheet> getDatasheetList() {
        return datasheetList;
    }
}
