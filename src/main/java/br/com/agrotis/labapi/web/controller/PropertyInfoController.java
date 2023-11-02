package br.com.agrotis.labapi.web.controller;

import br.com.agrotis.labapi.dto.PropertyInfoDTO;
import br.com.agrotis.labapi.service.PropertyInfoService;
import br.com.agrotis.labapi.web.api.PropertyInfoAPI;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoCreateRequest;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PropertyInfoController implements PropertyInfoAPI {

    private final PropertyInfoService propertyInfoService;

    public ResponseEntity<PropertyInfoDTO> retrievePropertyInfosById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyInfoService.retrievePropertyInfoById(id));
    }

    public ResponseEntity<List<PropertyInfoDTO>> retrieveAllPropertyInfos() {
        return ResponseEntity.ok(propertyInfoService.retrieveAllPropertiesInfo());
    }

    public ResponseEntity<PropertyInfoDTO> addPropertyInfo(@RequestBody PropertyInfoCreateRequest propertyInfoUpdateRequest) throws URISyntaxException {
        PropertyInfoDTO createdProperty = propertyInfoService.addPropertyInfo(propertyInfoUpdateRequest.getName());

        String resourceUrl = "/v1/person";

        return ResponseEntity.created(new URI(resourceUrl)).body(createdProperty);
    }

    public ResponseEntity<PropertyInfoDTO> updatePropertyInfo(
            @RequestBody PropertyInfoUpdateRequest propertyInfoUpdateRequest) {
        return ResponseEntity.ok(propertyInfoService.updatePropertyInfo(propertyInfoUpdateRequest));
    }

    public ResponseEntity<Void> deletePropertyInfo(@PathVariable Long id) {
        propertyInfoService.deletePropertyInfo(id);
        return ResponseEntity.noContent().build();
    }
}
