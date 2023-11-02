package br.com.agrotis.labapi.service;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import br.com.agrotis.labapi.domain.repository.LaboratoryRepository;
import br.com.agrotis.labapi.dto.LaboratoryDTO;
import br.com.agrotis.labapi.exception.LaboratoryAlreadyExistsException;
import br.com.agrotis.labapi.exception.LaboratoryNotFoundException;
import br.com.agrotis.labapi.mapper.LaboratoryMapper;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaboratoryService {

    private final LaboratoryRepository laboratoryRepository;

    private final LaboratoryMapper laboratoryMapper;

    public LaboratoryDTO retrieveLaboratoryById(Long id) {
        Optional<LaboratoryEntity> laboratoryEntity = laboratoryRepository.findById(id);

        if (laboratoryEntity.isEmpty()) {
            throw new LaboratoryNotFoundException("Laboratory not found in database");
        }

        LaboratoryEntity laboratory = laboratoryEntity.get();
        return laboratoryMapper.toDTO(laboratory);
    }

    public List<LaboratoryDTO> retrieveAllLaboratories() {
        return laboratoryRepository.findAll().stream().map(laboratoryMapper::toDTO).collect(Collectors.toList());
    }

    public LaboratoryDTO addLaboratory(String laboratoryName) {
        Optional<LaboratoryEntity> laboratoryEntity = Optional.ofNullable(laboratoryRepository.findByName(laboratoryName));

        laboratoryEntity.ifPresent(laboratory -> {
            throw new LaboratoryAlreadyExistsException("Already exists laboratory with this name");
        });

        LaboratoryEntity savedLaboratory = laboratoryRepository.save(LaboratoryEntity.builder().name(laboratoryName).build());
        return laboratoryMapper.toDTO(savedLaboratory);
    }

    public LaboratoryDTO updateLaboratory(LaboratoryUpdateRequest request) {
        Optional<LaboratoryEntity> laboratoryEntity = laboratoryRepository.findById(request.getId());

        if (laboratoryEntity.isEmpty()) {
            throw new LaboratoryNotFoundException("Laboratory not found in database");
        }

        laboratoryEntity.get().setName(request.getName());

        return laboratoryMapper.toDTO(laboratoryRepository.save(laboratoryEntity.get()));
    }

    public void deleteLaboratory(Long id) {
        Optional<LaboratoryEntity> laboratoryEntity = laboratoryRepository.findById(id);

        if (laboratoryEntity.isEmpty()) {
            throw new LaboratoryNotFoundException("Laboratory not found in database");
        }

        laboratoryRepository.delete(laboratoryEntity.get());
    }
}
