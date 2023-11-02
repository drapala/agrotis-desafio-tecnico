package br.com.agrotis.labapi.web.api;

import br.com.agrotis.labapi.dto.LaboratoryDTO;
import br.com.agrotis.labapi.dto.LaboratoryWithCountDTO;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryCreateRequest;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@Tag(name = "Laboratories")
@RequestMapping(value = "/v1")
public interface LaboratoryAPI {

    @GetMapping(
            path = "/laboratory/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "return laboratory by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned the laboratory successfully"),
            @ApiResponse(responseCode = "404", description = "laboratory not found in database"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<LaboratoryDTO> retrieveLaboratoryById(@PathVariable Long id);

    @GetMapping(
            path = "people/laboratory/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "return people count by laboratory id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned the list of people successfully"),
            @ApiResponse(responseCode = "404", description = "could not find the people by this laboratory"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<Long> retrievePeopleCount(@PathVariable Long laboratoryId);

    @GetMapping("/laboratory")
    @Operation(summary = "return all laboratories on database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned all laboratories successfully"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<List<LaboratoryDTO>> retrieveAllLaboratories();

    @PostMapping("/laboratory")
    @Operation(summary = "add laboratory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created the laboratory successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<LaboratoryDTO> addLaboratory(@RequestBody LaboratoryCreateRequest laboratoryCreateRequest);

    @PutMapping("/laboratory")
    @Operation(summary = "update the laboratory in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "updated the laboratory successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<LaboratoryDTO> updateLaboratory(@RequestBody LaboratoryUpdateRequest laboratoryUpdateRequest);

    @DeleteMapping("/laboratory/{id}")
    @Operation(summary = "delete the laboratory in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "deleted the laboratory successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<Void> deleteLaboratory(@PathVariable Long id);

    @GetMapping("laboratory/filter")
    @Operation(summary = "retrieve laboratories with filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned the laboratories filtered successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<List<LaboratoryWithCountDTO>> retrieveLaboratoriesWithFilters(
            @RequestParam(name = "initialDateStart", required = false) Instant initialDateStart,
            @RequestParam(name = "initialDateEnd", required = false) Instant initialDateEnd,
            @RequestParam(name = "finalDateStart", required = false) Instant finalDateStart,
            @RequestParam(name = "finalDateEnd", required = false) Instant finalDateEnd,
            @RequestParam(name = "searchObservation", required = false) String searchObservation,
            @RequestParam(name = "orderBy", required = false) String orderBy
    );
}
