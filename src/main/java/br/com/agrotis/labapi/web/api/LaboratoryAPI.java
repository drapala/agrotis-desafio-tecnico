package br.com.agrotis.labapi.web.api;

import br.com.agrotis.labapi.dto.LaboratoryDTO;
import br.com.agrotis.labapi.dto.LaboratoryWithCountDTO;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryCreateRequest;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;

@Tag(name = "Laboratories")
@RequestMapping(value = "/v1")
public interface LaboratoryAPI {

    @GetMapping(
            path = "/laboratory/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Return laboratory by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned the laboratory successfully."),
            @ApiResponse(responseCode = "404", description = "Laboratory not found in database."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<LaboratoryDTO> retrieveLaboratoryById(@PathVariable Long id);

    @GetMapping("/laboratory")
    @Operation(summary = "Return all laboratories on database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all laboratories successfully."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<List<LaboratoryDTO>> retrieveAllLaboratories();

    @PostMapping("/laboratory")
    @Operation(summary = "Add laboratory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the laboratory successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<LaboratoryDTO> addLaboratory(@RequestBody LaboratoryCreateRequest laboratoryCreateRequest) throws URISyntaxException;

    @PutMapping("/laboratory")
    @Operation(summary = "Update the laboratory in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Updated the laboratory successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<LaboratoryDTO> updateLaboratory(@RequestBody LaboratoryUpdateRequest laboratoryUpdateRequest);

    @DeleteMapping("/laboratory/{id}")
    @Operation(summary = "Delete the laboratory in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the laboratory successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<Void> deleteLaboratory(@PathVariable Long id);

    @GetMapping("laboratory/filter")
    @Operation(summary = "Retrieve laboratories with filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned the laboratories filtered successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<List<LaboratoryWithCountDTO>> retrieveLaboratoriesWithFilters(
            @RequestParam(name = "initialDateStart", required = false) Instant initialDateStart,
            @RequestParam(name = "initialDateEnd", required = false) Instant initialDateEnd,
            @RequestParam(name = "finalDateStart", required = false) Instant finalDateStart,
            @RequestParam(name = "finalDateEnd", required = false) Instant finalDateEnd,
            @RequestParam(name = "searchObservation", required = false) String searchObservation,
            @Schema(description = "order by values", allowableValues = {"peopleCount", "initialDate"}, example = "peopleCount")
            @RequestParam(name = "orderBy", required = false) String orderBy
    );
}
