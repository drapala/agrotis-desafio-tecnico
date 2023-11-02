package br.com.agrotis.labapi.service;

import br.com.agrotis.labapi.IntegrationTest;
import br.com.agrotis.labapi.domain.entity.PropertyInfoEntity;
import br.com.agrotis.labapi.domain.repository.LaboratoryRepository;
import br.com.agrotis.labapi.domain.repository.PersonRepository;
import br.com.agrotis.labapi.domain.repository.PropertyInfoRepository;
import br.com.agrotis.labapi.dto.PropertyInfoDTO;
import br.com.agrotis.labapi.exception.PersonNotFoundException;
import br.com.agrotis.labapi.web.request.PersonRequest;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryRequest;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@IntegrationTest
class PersonServiceTest {

    @MockBean
    private LaboratoryRepository laboratoryRepository;

    @MockBean
    private PropertyInfoRepository propertyInfoRepository;

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService service;

    @Test
    void givenAnRequest_whenCallRetrievePersonByIdAndNotFound_ShouldThrowsNotFound() {
        final var expectedMessage = "Person not found.";

        when(personRepository.findById(anyLong()))
                .thenReturn(Optional.empty());
        final var exceptionMessage = assertThrows(PersonNotFoundException.class,
                ()-> service.retrievePersonById(anyLong())).getMessage();

       assertEquals(expectedMessage, exceptionMessage);
    }
}
