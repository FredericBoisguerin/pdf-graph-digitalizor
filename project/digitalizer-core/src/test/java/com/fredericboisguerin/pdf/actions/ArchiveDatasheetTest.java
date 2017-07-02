package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArchiveDatasheetTest {
    @Test
    public void should_ask_service_to_archive_datasheet() throws Exception {
        DatasheetService datasheetService = mock(DatasheetService.class);
        String datasheetId = "TEST";
        ArchiveDatasheet archiveDatasheet = new ArchiveDatasheet(datasheetId);

        archiveDatasheet.execute(datasheetService);

        verify(datasheetService).archive(datasheetId);
    }
}