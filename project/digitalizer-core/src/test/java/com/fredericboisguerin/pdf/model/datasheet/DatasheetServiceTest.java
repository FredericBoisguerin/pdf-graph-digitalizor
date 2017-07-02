package com.fredericboisguerin.pdf.model.datasheet;

import com.fredericboisguerin.pdf.infrastructure.DatasheetRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class DatasheetServiceTest {

    @Mock private DatasheetRepository datasheetRepository;
    private DatasheetService datasheetService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        datasheetService = new DatasheetService(datasheetRepository);
    }

    @Test
    public void should_store_to_the_repository_with_new_metadata() throws Exception {
        Datasheet datasheet = createDatasheet();
        DatasheetMetaInfo expectedDatasheetMetaInfo = mock(DatasheetMetaInfo.class);

        datasheetService.setDatasheetMetaInfo(datasheet.getId(), expectedDatasheetMetaInfo);

        verify(datasheetRepository).save(datasheet);
        assertThat(datasheet.getDatasheetMetaInfo()).isEqualTo(expectedDatasheetMetaInfo);
    }

    @Test
    public void should_archive_datasheet_and_store() throws Exception {
        Datasheet datasheet = createDatasheet();

        datasheetService.archive(datasheet.getId());

        verify(datasheetRepository).save(datasheet);
        assertThat(datasheet.isArchived()).isTrue();
    }

    private Datasheet createDatasheet() {
        Datasheet datasheet = new Datasheet(mock(DatasheetMetaInfo.class));
        String datasheetId = datasheet.getId();
        when(datasheetRepository.findById(datasheetId)).thenReturn(Optional.of(datasheet));
        return datasheet;
    }

    @Test
    public void should_remove_graph_and_store_datasheet() throws Exception {
        Datasheet datasheet = createDatasheet();
        DatasheetGraph datasheetGraph = mock(DatasheetGraph.class);
        String graphId = "graphId";
        when(datasheetGraph.getId()).thenReturn(graphId);
        datasheet.addGraph(datasheetGraph);

        assertThat(datasheet.getDatasheetGraphs(graphId)).isNotEmpty();

        datasheetService.removeGraph(datasheet.getId(), graphId);

        verify(datasheetRepository).save(datasheet);
        assertThat(datasheet.getDatasheetGraphs(graphId)).isEmpty();
    }
}