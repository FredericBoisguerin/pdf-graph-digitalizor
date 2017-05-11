package com.fredericboisguerin.pdf.infrastructure;

import com.fredericboisguerin.pdf.model.datasheet.Datasheet;

import java.util.*;

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

    @Override
    public Optional<Datasheet> findById(String parameter) {
        return datasheets.stream()
                         .filter(datasheet -> datasheet.idEquals(parameter))
                         .findFirst();
    }
}
