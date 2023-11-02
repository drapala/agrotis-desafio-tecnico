package br.com.agrotis.labapi.service;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import br.com.agrotis.labapi.domain.entity.PersonEntity;
import br.com.agrotis.labapi.domain.entity.PropertyInfoEntity;
import br.com.agrotis.labapi.domain.repository.LaboratoryRepository;
import br.com.agrotis.labapi.domain.repository.PersonRepository;
import br.com.agrotis.labapi.domain.repository.PropertyInfoRepository;
import br.com.agrotis.labapi.dto.PersonDTO;
import br.com.agrotis.labapi.exception.LaboratoryNotFoundException;
import br.com.agrotis.labapi.exception.PersonNotFoundException;
import br.com.agrotis.labapi.exception.PropertyInfoNotFoundException;
import br.com.agrotis.labapi.mapper.PersonMapper;
import br.com.agrotis.labapi.web.request.PersonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
            throw new PropertyInfoNotFoundException();
        }

        PersonEntity personInfo = personEntity.get();
        return personMapper.toDTO(personInfo);
    }

    public List<PersonDTO> retrieveAllPeople() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO addPerson(PersonRequest personRequest) {
        LaboratoryEntity laboratory = laboratoryRepository
                .findById(personRequest.getLaboratory().getId())
                .orElseThrow(LaboratoryNotFoundException::new);

        PropertyInfoEntity propertyInfo = propertyInfoRepository
                .findById(personRequest.getPropertyInfo().getId())
                .orElseThrow(PropertyInfoNotFoundException::new);

        PersonEntity personEntity = personMapper.toEntity(personRequest);
        personEntity.setLaboratory(laboratory);
        personEntity.setPropertyInfo(propertyInfo);

        PersonEntity savedPerson = personRepository.save(personEntity);
        return personMapper.toDTO(savedPerson);
    }

    public PersonDTO updatePerson(Long id, PersonRequest personRequest) {
        Optional<PersonEntity> personEntity = personRepository.findById(id);

        if (personEntity.isEmpty()) {
            throw new PersonNotFoundException();
        }

        personEntity.get().setName(personRequest.getName());

        return personMapper.toDTO(personRepository.save(personEntity.get()));
    }

    public void deletePerson(Long id) {
        Optional<PersonEntity> personEntity = personRepository.findById(id);

        if (personEntity.isEmpty()) {
            throw new PersonNotFoundException();
        }

        personRepository.delete(personEntity.get());
    }

    public List<PersonDTO> retrievePeopleByInitialDateRange(Instant startDate, Instant endDate) {
        return personRepository.findByInitialDateBetween(startDate, endDate)
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PersonDTO> retrievePeopleByFinalDateRange(Instant startDate, Instant endDate) {
        return personRepository.findByFinalDateBetween(startDate, endDate)
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PersonDTO> retrievePeopleByObservation(String observation) {
        return personRepository.findByObservation(observation)
                .stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
}
