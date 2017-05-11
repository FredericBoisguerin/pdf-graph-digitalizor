package com.fredericboisguerin.pdf.infrastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fredericboisguerin.pdf.model.datasheet.Datasheet;

public class InMemoryDatasheetRepository implements DatasheetRepository {

    private final List<Datasheet> datasheets = new ArrayList<>();

    @Override
    public void create(Datasheet datasheet) {
        datasheets.add(datasheet);
    }

    @Override
    public Collection<Datasheet> findAll() {
        return Collections.unmodifiableCollection(datasheets);
    }
}
