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

import java.net.URISyntaxException;
import java.util.List;

@Tag(name = "People")
@RequestMapping(value = "/v1")
public interface PersonAPI {

    @GetMapping(
            path = "/person/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Return person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned the person successfully."),
            @ApiResponse(responseCode = "404", description = "Could not find the person in database."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<PersonDTO> retrievePersonById(@PathVariable Long id);

    @GetMapping(
            path = "/person",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Return all people registered in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all people successfully."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<List<PersonDTO>> retrieveAllPeople();

    @PostMapping(
            path = "/person",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Add a person on database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created person successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<PersonDTO> addPerson(@RequestBody PersonRequest personRequest) throws URISyntaxException;

    @DeleteMapping(
            path = "/person/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Delete a person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<Void> deletePerson(@PathVariable Long id);
}
