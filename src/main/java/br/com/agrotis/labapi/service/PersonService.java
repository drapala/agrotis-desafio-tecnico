package br.com.agrotis.labapi.service;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import br.com.agrotis.labapi.domain.entity.PersonEntity;
import br.com.agrotis.labapi.domain.entity.PropertyInfoEntity;
import br.com.agrotis.labapi.domain.repository.LaboratoryRepository;
import br.com.agrotis.labapi.domain.repository.PersonRepository;
import br.com.agrotis.labapi.domain.repository.PropertyInfoRepository;
import br.com.agrotis.labapi.dto.PersonDTO;
import br.com.agrotis.labapi.exception.PersonAlreadyExistsException;
import br.com.agrotis.labapi.exception.PersonNotFoundException;
import br.com.agrotis.labapi.exception.PropertyInfoNotFoundException;
import br.com.agrotis.labapi.mapper.PersonMapper;
import br.com.agrotis.labapi.web.request.PersonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private final LaboratoryRepository laboratoryRepository;

    private final PropertyInfoRepository propertyInfoRepository;

    private final PersonMapper personMapper;

    public PersonDTO retrievePersonById(Long id) {
        Optional<PersonEntity> personEntity = personRepository.findById(id);

        if (personEntity.isEmpty()) {
            throw new PropertyInfoNotFoundException("Person not found in database");
        }

        PersonEntity personInfo = personEntity.get();
        return personMapper.toDTO(personInfo);
    }

    public List<PersonDTO> retrieveAllPeople() {
        return personRepository.findAll().stream().map(personMapper::toDTO).collect(Collectors.toList());
    }

    public PersonDTO addPerson(PersonRequest personRequest) {
        Optional<LaboratoryEntity> laboratory = laboratoryRepository.findById(personRequest.getLaboratory().getId());
        Optional<PropertyInfoEntity> propertyInfo = propertyInfoRepository.findById(personRequest.getPropertyInfo().getId());

        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(personRequest.getName());
        personEntity.setInitialDate(personRequest.getInitialDate());
        personEntity.setFinalDate(personRequest.getFinalDate());
        personEntity.setPropertyInfo(propertyInfo.get());
        personEntity.setLaboratory(laboratory.get());
        personEntity.setObservation(personRequest.getObservation());
        PersonEntity savedPerson = personRepository.save(personEntity);

        return personMapper.toDTO(savedPerson);
    }

    public PersonDTO updatePerson(Long id, PersonRequest personRequest) {
        Optional<PersonEntity> personEntity = personRepository.findById(id);

        if (personEntity.isEmpty()) {
            throw new PersonNotFoundException("Person not found in database");
        }

        personEntity.get().setName(personRequest.getName());

        return personMapper.toDTO(personRepository.save(personEntity.get()));
    }

    public void deletePerson(Long id) {
        Optional<PersonEntity> personEntity = personRepository.findById(id);

        if (personEntity.isEmpty()) {
            throw new PersonNotFoundException("Person not found in database");
        }

        personRepository.delete(personEntity.get());
    }
}