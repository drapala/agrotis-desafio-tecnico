package br.com.agrotis.labapi.web.api;

import br.com.agrotis.labapi.dto.PropertyInfoDTO;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoCreateRequest;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@Tag(name = "Property-Info")
@RequestMapping(value = "/v1")
public interface PropertyInfoAPI {

    @GetMapping(
            path = "/property-info/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Return property-info by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned property-info successfully."),
            @ApiResponse(responseCode = "404", description = "Could not find the property-info  on database."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<PropertyInfoDTO> retrievePropertyInfosById(@PathVariable final Long id);

    @GetMapping(
            path = "/property-info/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Return all property-info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned successfully."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<List<PropertyInfoDTO>> retrieveAllPropertyInfos();

    @PostMapping(
            path = "/property-info/",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Add a property-info on database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<PropertyInfoDTO> addPropertyInfo(@RequestBody final PropertyInfoCreateRequest propertyInfoUpdateRequest) throws URISyntaxException;

    @PutMapping(path = "/property-info/")
    @Operation(summary = "Update a property-info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully."),
            @ApiResponse(responseCode = "404", description = "Could not find the property-info."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<PropertyInfoDTO> updatePropertyInfo(
            @RequestBody final PropertyInfoUpdateRequest propertyInfoUpdateRequest
    );

    @DeleteMapping(path = "/property-info/{id}")
    @Operation(summary = "Delete a property-info by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid parameters, check out your request."),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown.")
    })
    ResponseEntity<Void> deletePropertyInfo(@PathVariable final Long id);
}
