package com.fredericboisguerin.pdf.model.datasheet;

import com.fredericboisguerin.pdf.infrastructure.DatasheetRepository;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DatasheetServiceTest {

    @Test
    public void should_store_to_the_repository_with_new_metadata() throws Exception {
        DatasheetRepository datasheetRepository = mock(DatasheetRepository.class);
        DatasheetService datasheetService = new DatasheetService(datasheetRepository);
        Datasheet datasheet = new Datasheet(mock(DatasheetMetaInfo.class));
        String datasheetId = datasheet.getId();
        when(datasheetRepository.findById(datasheetId)).thenReturn(Optional.of(datasheet));
        DatasheetMetaInfo expectedDatasheetMetaInfo = mock(DatasheetMetaInfo.class);

        datasheetService.setDatasheetMetaInfo(datasheetId, expectedDatasheetMetaInfo);

        verify(datasheetRepository).save(datasheet);
        assertThat(datasheet.getDatasheetMetaInfo()).isEqualTo(expectedDatasheetMetaInfo);
    }

    @Test
    public void should_archive_datasheet_and_store() throws Exception {
        DatasheetRepository datasheetRepository = mock(DatasheetRepository.class);
        DatasheetService datasheetService = new DatasheetService(datasheetRepository);
        Datasheet datasheet = new Datasheet(mock(DatasheetMetaInfo.class));
        String datasheetId = datasheet.getId();
        when(datasheetRepository.findById(datasheetId)).thenReturn(Optional.of(datasheet));

        datasheetService.archive(datasheetId);

        verify(datasheetRepository).save(datasheet);
        assertThat(datasheet.isArchived()).isTrue();
    }
}