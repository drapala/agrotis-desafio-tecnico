package br.com.agrotis.labapi.web.controller;

import br.com.agrotis.labapi.dto.PropertyInfoDTO;
import br.com.agrotis.labapi.service.PropertyInfoService;
import br.com.agrotis.labapi.web.api.PropertyInfoAPI;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoCreateRequest;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PropertyInfoController implements PropertyInfoAPI {

    private final PropertyInfoService propertyInfoService;

    public ResponseEntity<PropertyInfoDTO> retrieveAllPropertyInfos(@PathVariable Long id) {
        return ResponseEntity.ok(propertyInfoService.retrievePropertyInfoById(id));
    }

    public ResponseEntity<List<PropertyInfoDTO>> retrieveAllPropertyInfos() {
        return ResponseEntity.ok(propertyInfoService.retrieveAllPropertiesInfo());
    }

    public ResponseEntity<PropertyInfoDTO> addPropertyInfo(@RequestBody PropertyInfoCreateRequest propertyInfoUpdateRequest) {
        return ResponseEntity.ok(propertyInfoService.addPropertyInfo(propertyInfoUpdateRequest.getName()));
    }

    public ResponseEntity<PropertyInfoDTO> updatePropertyInfo(
            @RequestBody PropertyInfoUpdateRequest propertyInfoUpdateRequest) {
        return ResponseEntity.ok(propertyInfoService.updatePropertyInfo(propertyInfoUpdateRequest));
    }

    public ResponseEntity<Void> deletePropertyInfo(@PathVariable Long id) {
        propertyInfoService.deletePropertyInfo(id);
        return ResponseEntity.ok().build();
    }
}
