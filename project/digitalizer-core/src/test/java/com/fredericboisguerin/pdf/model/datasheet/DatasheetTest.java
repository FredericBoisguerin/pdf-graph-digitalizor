package com.fredericboisguerin.pdf.model.datasheet;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class DatasheetTest {

    @Test
    public void should_not_be_archived_by_default() throws Exception {
        Datasheet datasheet = createDatasheet();

        assertThat(datasheet.isArchived()).isFalse();
    }

    @Test
    public void should_be_archived_after_archive() throws Exception {
        Datasheet datasheet = createDatasheet();

        datasheet.archive();

        assertThat(datasheet.isArchived()).isTrue();
    }

    private Datasheet createDatasheet() {
        return new Datasheet(mock(DatasheetMetaInfo.class));
    }
}