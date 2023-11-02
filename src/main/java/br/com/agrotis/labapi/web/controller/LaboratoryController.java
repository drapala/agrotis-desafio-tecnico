package br.com.agrotis.labapi.web.controller;

import br.com.agrotis.labapi.dto.LaboratoryDTO;
import br.com.agrotis.labapi.dto.LaboratoryWithCountDTO;
import br.com.agrotis.labapi.dto.PersonDTO;
import br.com.agrotis.labapi.service.LaboratoryService;
import br.com.agrotis.labapi.web.api.LaboratoryAPI;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryCreateRequest;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LaboratoryController implements LaboratoryAPI {

    private final LaboratoryService laboratoryService;

    public ResponseEntity<LaboratoryDTO> retrieveLaboratoryById(@PathVariable Long id) {
        return ResponseEntity.ok(laboratoryService.retrieveLaboratoryById(id));
    }

    public ResponseEntity<List<LaboratoryWithCountDTO>> retrieveLaboratoriesWithFilters(
            Instant initialDateStart,
            Instant initialDateEnd,
            Instant finalDateStart,
            Instant finalDateEnd,
            String searchObservation,
            String orderBy
    ) {
        List<LaboratoryWithCountDTO> laboratories = laboratoryService.retrieveLaboratoriesWithFilters(
                initialDateStart, initialDateEnd, finalDateStart, finalDateEnd, searchObservation, orderBy
        );

        return ResponseEntity.ok(laboratories);
    }

    public ResponseEntity<List<LaboratoryDTO>> retrieveAllLaboratories() {
        return ResponseEntity.ok(laboratoryService.retrieveAllLaboratories());
    }

    public ResponseEntity<LaboratoryDTO> addLaboratory(@RequestBody LaboratoryCreateRequest laboratoryCreateRequest) throws URISyntaxException {
        LaboratoryDTO createdLaboratory = laboratoryService.addLaboratory(laboratoryCreateRequest.getName());

        String resourceUrl = "/v1/person";

        return ResponseEntity.created(new URI(resourceUrl)).body(createdLaboratory);
    }

    public ResponseEntity<LaboratoryDTO> updateLaboratory(@RequestBody LaboratoryUpdateRequest laboratoryUpdateRequest) {
        return ResponseEntity.ok(laboratoryService.updateLaboratory(laboratoryUpdateRequest));
    }

    public ResponseEntity<Void> deleteLaboratory(@PathVariable Long id) {
        laboratoryService.deleteLaboratory(id);
        return ResponseEntity.noContent().build();
    }
}
