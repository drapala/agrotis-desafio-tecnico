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

import java.util.List;

@Tag(name = "Property-Info")
@RequestMapping(value = "/v1")
public interface PropertyInfoAPI {

    @GetMapping(
            path = "/property-info/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "return property-info by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned property-info successfully"),
            @ApiResponse(responseCode = "404", description = "could not find the property-info  on database"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<PropertyInfoDTO> retrieveAllPropertyInfos(@PathVariable final Long id);

    @GetMapping(
            path = "/property-info/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "return all property-info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returned successfully"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<List<PropertyInfoDTO>> retrieveAllPropertyInfos();

    @PostMapping(
            path = "/property-info/",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "add a property-info on database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "created successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<PropertyInfoDTO> addPropertyInfo(@RequestBody final PropertyInfoCreateRequest propertyInfoUpdateRequest);

    @PutMapping(path = "/property-info/")
    @Operation(summary = "update a property-info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "updated successfully"),
            @ApiResponse(responseCode = "404", description = "could not find the property-info"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<PropertyInfoDTO> updatePropertyInfo(
            @RequestBody final PropertyInfoUpdateRequest propertyInfoUpdateRequest
    );

    @DeleteMapping(path = "/property-info/{id}")
    @Operation(summary = "delete a property-info by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "deleted successfully"),
            @ApiResponse(responseCode = "400", description = "invalid parameters, check out your request"),
            @ApiResponse(responseCode = "500", description = "An internal error was thrown")
    })
    ResponseEntity<Void> deletePropertyInfo(@PathVariable final Long id);
}
