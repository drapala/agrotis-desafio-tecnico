package br.com.agrotis.labapi.web.controller;

import br.com.agrotis.labapi.dto.PersonDTO;
import br.com.agrotis.labapi.service.PersonService;
import br.com.agrotis.labapi.web.request.PersonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/person")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> retrieveAllPeople(@PathVariable Long id) {
        return ResponseEntity.ok(personService.retrievePersonById(id));
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> retrieveAllpeople() {
        return ResponseEntity.ok(personService.retrieveAllPeople());
    }

    @PostMapping
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok(personService.addPerson(personRequest));
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updatePerson(
            @PathVariable Long id,
            @RequestBody PersonRequest personRequest
    ) {
        return ResponseEntity.ok(personService.updatePerson(id, personRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().build();
    }
}