package br.com.agrotis.labapi.service;

import br.com.agrotis.labapi.IntegrationTest;
import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import br.com.agrotis.labapi.domain.entity.PropertyInfoEntity;
import br.com.agrotis.labapi.domain.repository.PropertyInfoRepository;
import br.com.agrotis.labapi.dto.LaboratoryDTO;
import br.com.agrotis.labapi.dto.PropertyInfoDTO;
import br.com.agrotis.labapi.exception.PropertyInfoNotFoundException;
import br.com.agrotis.labapi.mapper.PropertyInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@IntegrationTest
class PropertyInfoServiceTest {

    @SpyBean
    private PropertyInfoMapper propertyInfoMapper;

    @SpyBean
    PropertyInfoRepository repository;

    @Autowired
    PropertyInfoService service;


    @Test
    void givenAnRequest_whenCallPropertyInfoByIdAndNotFound_ShouldThrowsNotFound() {
        final var expectedMessage = "Property info not found.";

        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());
        final var exceptionMessage = assertThrows(PropertyInfoNotFoundException.class,
                ()-> service.retrievePropertyInfoById(anyLong())).getMessage();

        assertEquals(expectedMessage, exceptionMessage);
    }

    @Test
    void givenAnRequest_whenCallAddPropertyInfo_ShouldReturnSuccessful() {
        PropertyInfoDTO propertyInfo = service.addPropertyInfo("Property Name");

        Optional<PropertyInfoEntity> retrievedLaboratory = repository.findById(propertyInfo.getId());

        assertTrue(retrievedLaboratory.isPresent());
        assertEquals(propertyInfo.getName(), retrievedLaboratory.get().getName());
    }
}
