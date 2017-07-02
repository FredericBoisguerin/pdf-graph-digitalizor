package com.fredericboisguerin.pdf.actions;

import com.fredericboisguerin.pdf.model.datasheet.DatasheetService;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RemoveDatasheetGraphTest {

    @Test
    public void should_call_datasheet_service_with_datasheetId_and_graphId() throws Exception {
        String datasheetId = "datasheet";
        String graphId = "graph";
                RemoveDatasheetGraph removeDatasheetGraph = new RemoveDatasheetGraph(datasheetId, graphId);
        DatasheetService datasheetService = mock(DatasheetService.class);

        removeDatasheetGraph.execute(datasheetService);

        verify(datasheetService).removeGraph(datasheetId, graphId);
    }
}