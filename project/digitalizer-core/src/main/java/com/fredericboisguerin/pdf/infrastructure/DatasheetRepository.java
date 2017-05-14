package com.fredericboisguerin.pdf.infrastructure;

import java.util.Collection;
import java.util.Optional;

import com.fredericboisguerin.pdf.model.datasheet.Datasheet;

public interface DatasheetRepository {
    void save(Datasheet datasheet);

    Collection<Datasheet> findAll();

    Optional<Datasheet> findById(String parameter);
}
