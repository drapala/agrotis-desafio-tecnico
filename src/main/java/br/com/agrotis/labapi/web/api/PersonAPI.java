package br.com.agrotis.labapi.web.api;

import br.com.agrotis.labapi.dto.PersonDTO;
import br.com.agrotis.labapi.web.request.PersonRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@Tag(name = "People")
@RequestMapping(value = "/v1")
public interface PersonAPI {

    @GetMapping(
            path = "/person/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "return person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned the person successfully"),
            @ApiResponse(responseCode = "404", description = "could not find the person in database"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<PersonDTO> retrievePersonById(@PathVariable Long id);

    @GetMapping(
            path = "/person",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "return all people registered in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned all people successfully"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<List<PersonDTO>> retrieveAllPeople();

    @PostMapping(
            path = "/person",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "add a person on database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created person successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<PersonDTO> addPerson(@RequestBody PersonRequest personRequest);

    @DeleteMapping(
            path = "/person/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "delete a person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "deleted successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<Void> deletePerson(@PathVariable Long id);

    @GetMapping(
            path = "/person/filter/initial",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "return people filtered by initial date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned the list filtered successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<List<PersonDTO>> retrievePeopleByInitialDateRange(
            @RequestParam Instant startDate,
            @RequestParam Instant endDate
    );

    @GetMapping(
            path = "/person/filter/final",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "return people filtered by final date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned the list filtered successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<List<PersonDTO>> retrievePeopleByFinalDateRange(
            @RequestParam Instant startDate,
            @RequestParam Instant endDate
    );

    @GetMapping(
            path = "person/filter/observation",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "return people filtered by observation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned the list filtered successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<List<PersonDTO>> retrievePeopleByObservation(
            @RequestParam String keyword
    );

}
