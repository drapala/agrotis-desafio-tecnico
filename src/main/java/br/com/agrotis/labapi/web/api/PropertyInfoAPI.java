package br.com.agrotis.labapi.web.api;

import br.com.agrotis.labapi.dto.PropertyInfoDTO;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoCreateRequest;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoUpdateRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/property-info")
public interface PropertyInfoAPI {

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<PropertyInfoDTO> retrieveAllPropertyInfos(@PathVariable final Long id);

    @GetMapping
    ResponseEntity<List<PropertyInfoDTO>> retrieveAllPropertyInfos();

    @PostMapping
    ResponseEntity<PropertyInfoDTO> addPropertyInfo(@RequestBody final PropertyInfoCreateRequest propertyInfoUpdateRequest);

    @PutMapping
    ResponseEntity<PropertyInfoDTO> updatePropertyInfo(
            @RequestBody final PropertyInfoUpdateRequest propertyInfoUpdateRequest
    );

    @DeleteMapping(path = "/{id}")
    ResponseEntity<Void> deletePropertyInfo(@PathVariable final Long id);
}
