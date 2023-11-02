package br.com.agrotis.labapi.service;

import br.com.agrotis.labapi.IntegrationTest;
import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import br.com.agrotis.labapi.domain.repository.LaboratoryRepository;
import br.com.agrotis.labapi.dto.LaboratoryDTO;
import br.com.agrotis.labapi.exception.LaboratoryNotFoundException;
import br.com.agrotis.labapi.mapper.LaboratoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@IntegrationTest
class LaboratoryServiceTest {

    @SpyBean
    private LaboratoryMapper laboratoryMapper;

    @SpyBean
    private LaboratoryRepository repository;

    @Autowired
    private LaboratoryService service;


    @Test
    void givenAnRequest_whenCallRetrieveLaboratoryByIdAndNotFound_ShouldThrowsNotFound() {
        final var expectedMessage = "Laboratory not found.";

        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());
        final var exceptionMessage = assertThrows(LaboratoryNotFoundException.class,
                ()-> service.retrieveLaboratoryById(anyLong())).getMessage();

        assertEquals(expectedMessage, exceptionMessage);
    }

    @Test
    void givenAnRequest_whenCallAddLaboratory_ShouldReturnSuccessful() {
        LaboratoryDTO addedLaboratory = service.addLaboratory("Lab Name");

        Optional<LaboratoryEntity> retrievedLaboratory = repository.findById(addedLaboratory.getId());

        assertTrue(retrievedLaboratory.isPresent());
        assertEquals(addedLaboratory.getName(), retrievedLaboratory.get().getName());
    }

    @Test
    void givenAnValidRequest_whenCallDeleteLaboratory_shouldDelete() {
        var laboratoryId = 1L;
        var laboratory = new LaboratoryEntity(1L ,"Lab Name", anyList());
        repository.save(laboratory);

        Assertions.assertDoesNotThrow(()-> service.deleteLaboratory(laboratoryId));
    }



}
