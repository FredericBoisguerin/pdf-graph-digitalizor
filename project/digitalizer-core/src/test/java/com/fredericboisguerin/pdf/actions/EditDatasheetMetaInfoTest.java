package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetMetaInfo;
import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EditDatasheetMetaInfoTest {

    @Test
    public void should_call_DatasheetService_with_the_DatasheetMetaInfo() throws Exception {
        DatasheetMetaInfo datasheetMetaInfo = mock(DatasheetMetaInfo.class);
        String datasheetId = "test";
        EditDatasheetMetaInfo editDatasheetMetaInfo = new EditDatasheetMetaInfo(datasheetId, datasheetMetaInfo);
        DatasheetService datasheetService = mock(DatasheetService.class);

        editDatasheetMetaInfo.execute(datasheetService);

        verify(datasheetService).setDatasheetMetaInfo(datasheetId, datasheetMetaInfo);
    }
}