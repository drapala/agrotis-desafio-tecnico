package br.com.agrotis.labapi.web.controller;

import br.com.agrotis.labapi.dto.LaboratoryDTO;
import br.com.agrotis.labapi.service.LaboratoryService;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryCreateRequest;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/laboratory")
public class LaboratoryController {

    private final LaboratoryService laboratoryService;

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryDTO> retrieveAllLaboratories(@PathVariable Long id) {
        return ResponseEntity.ok(laboratoryService.retrieveLaboratoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<LaboratoryDTO>> retrieveAllLaboratories() {
        return ResponseEntity.ok(laboratoryService.retrieveAllLaboratories());
    }

    @PostMapping
    public ResponseEntity<LaboratoryDTO> addLaboratory(@RequestBody LaboratoryCreateRequest laboratoryCreateRequest) {
        return ResponseEntity.ok(laboratoryService.addLaboratory(laboratoryCreateRequest.getName()));
    }

    @PutMapping
    public ResponseEntity<LaboratoryDTO> updateLaboratory(@RequestBody LaboratoryUpdateRequest laboratoryUpdateRequest) {
        return ResponseEntity.ok(laboratoryService.updateLaboratory(laboratoryUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratory(@PathVariable Long id) {
        laboratoryService.deleteLaboratory(id);
        return ResponseEntity.ok().build();
    }
}
