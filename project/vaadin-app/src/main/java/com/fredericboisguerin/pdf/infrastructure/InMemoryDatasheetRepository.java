package com.fredericboisguerin.pdf.infrastructure;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fredericboisguerin.pdf.model.datasheet.Datasheet;

public class InMemoryDatasheetRepository implements DatasheetRepository {

    private final Map<String, Datasheet> datasheets = new HashMap<>();

    @Override
    public void save(Datasheet datasheet) {
        datasheets.put(datasheet.getId(), datasheet);
    }

    @Override
    public Collection<Datasheet> findAll() {
        return Collections.unmodifiableCollection(datasheets.values());
    }

    @Override
    public Optional<Datasheet> findById(String parameter) {
        return Optional.ofNullable(datasheets.get(parameter));
    }
}
