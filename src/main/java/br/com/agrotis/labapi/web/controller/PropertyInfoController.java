package br.com.agrotis.labapi.web.controller;

import br.com.agrotis.labapi.dto.PropertyInfoDTO;
import br.com.agrotis.labapi.service.PropertyInfoService;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoCreateRequest;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/property-info")
public class PropertyInfoController {

    private final PropertyInfoService propertyInfoService;

    @GetMapping("/{id}")
    public ResponseEntity<PropertyInfoDTO> retrieveAllPropertyInfos(@PathVariable Long id) {
        return ResponseEntity.ok(propertyInfoService.retrievePropertyInfoById(id));
    }

    @GetMapping
    public ResponseEntity<List<PropertyInfoDTO>> retrieveAllPropertyInfos() {
        return ResponseEntity.ok(propertyInfoService.retrieveAllPropertiesInfo());
    }

    @PostMapping
    public ResponseEntity<PropertyInfoDTO> addPropertyInfo(@RequestBody PropertyInfoCreateRequest propertyInfoUpdateRequest) {
        return ResponseEntity.ok(propertyInfoService.addPropertyInfo(propertyInfoUpdateRequest.getName()));
    }

    @PutMapping
    public ResponseEntity<PropertyInfoDTO> updatePropertyInfo(
            @RequestBody PropertyInfoUpdateRequest propertyInfoUpdateRequest
    ) {
        return ResponseEntity.ok(propertyInfoService.updatePropertyInfo(propertyInfoUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertyInfo(@PathVariable Long id) {
        propertyInfoService.deletePropertyInfo(id);
        return ResponseEntity.ok().build();
    }
}