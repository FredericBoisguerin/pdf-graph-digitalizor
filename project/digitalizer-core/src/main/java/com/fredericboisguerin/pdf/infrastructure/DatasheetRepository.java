package com.fredericboisguerin.pdf.infrastructure;

import java.util.Collection;

import com.fredericboisguerin.pdf.model.datasheet.Datasheet;

public interface DatasheetRepository {
    void create(Datasheet datasheet);

    Collection<Datasheet> findAll();
}
