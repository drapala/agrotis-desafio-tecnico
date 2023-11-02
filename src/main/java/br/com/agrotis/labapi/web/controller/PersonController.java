package br.com.agrotis.labapi.web.controller;

import br.com.agrotis.labapi.dto.PersonDTO;
import br.com.agrotis.labapi.service.PersonService;
import br.com.agrotis.labapi.web.api.PersonAPI;
import br.com.agrotis.labapi.web.request.PersonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController implements PersonAPI {

    private final PersonService personService;


    // TODO -> Mudar as ResponseEntity's para os status code's corretos.
    public ResponseEntity<PersonDTO> retrievePersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.retrievePersonById(id));
    }

    public ResponseEntity<List<PersonDTO>> retrieveAllPeople() {
        return ResponseEntity.ok(personService.retrieveAllPeople());
    }

    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok(personService.addPerson(personRequest));
    }

    public ResponseEntity<PersonDTO> updatePerson(
            @PathVariable Long id,
            @RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok(personService.updatePerson(id, personRequest));
    }

    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<PersonDTO>> retrievePeopleByInitialDateRange(Instant startDate, Instant endDate) {
        return ResponseEntity.ok(personService.retrievePeopleByInitialDateRange(startDate, endDate));
    }

    public ResponseEntity<List<PersonDTO>> retrievePeopleByFinalDateRange(Instant startDate, Instant endDate) {
        return ResponseEntity.ok(personService.retrievePeopleByFinalDateRange(startDate, endDate));
    }

    public ResponseEntity<List<PersonDTO>> retrievePeopleByObservation(String keyword) {
        return ResponseEntity.ok(personService.retrievePeopleByObservation(keyword));
    }
}