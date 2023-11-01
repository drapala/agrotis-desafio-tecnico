package br.com.agrotis.labapi.web.controller;

import br.com.agrotis.labapi.dto.LaboratoryDTO;
import br.com.agrotis.labapi.service.LaboratoryService;
import br.com.agrotis.labapi.web.request.LaboratoryRequest;
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
    public ResponseEntity<LaboratoryDTO> addLaboratory(@RequestBody LaboratoryRequest laboratoryRequest) {
        return ResponseEntity.ok(laboratoryService.addLaboratory(laboratoryRequest.getLaboratoryName()));
    }

    @PutMapping
    public ResponseEntity<LaboratoryDTO> updateLaboratory(@RequestBody LaboratoryRequest laboratoryRequest) {
        return ResponseEntity.ok(laboratoryService.updateLaboratory(laboratoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaboratory(@PathVariable Long id) {
        laboratoryService.deleteLaboratory(id);
        return ResponseEntity.ok().build();
    }
}
