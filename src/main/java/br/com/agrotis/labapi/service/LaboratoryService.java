package br.com.agrotis.labapi.service;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import br.com.agrotis.labapi.domain.entity.PersonEntity;
import br.com.agrotis.labapi.domain.repository.LaboratoryRepository;
import br.com.agrotis.labapi.dto.LaboratoryDTO;
import br.com.agrotis.labapi.dto.LaboratoryWithCountDTO;
import br.com.agrotis.labapi.exception.LaboratoryAlreadyExistsException;
import br.com.agrotis.labapi.exception.LaboratoryNotFoundException;
import br.com.agrotis.labapi.mapper.LaboratoryMapper;
import br.com.agrotis.labapi.web.request.laboratory.LaboratoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
            throw new LaboratoryNotFoundException();
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
            throw new LaboratoryAlreadyExistsException();
        });

        LaboratoryEntity savedLaboratory = laboratoryRepository.save(LaboratoryEntity.builder().name(laboratoryName).build());
        return laboratoryMapper.toDTO(savedLaboratory);
    }

    public LaboratoryDTO updateLaboratory(LaboratoryUpdateRequest request) {
        Optional<LaboratoryEntity> laboratoryEntity = laboratoryRepository.findById(request.getId());

        if (laboratoryEntity.isEmpty()) {
            throw new LaboratoryNotFoundException();
        }

        laboratoryEntity.get().setName(request.getName());

        return laboratoryMapper.toDTO(laboratoryRepository.save(laboratoryEntity.get()));
    }

    public void deleteLaboratory(Long id) {
        Optional<LaboratoryEntity> laboratoryEntity = laboratoryRepository.findById(id);

        if (laboratoryEntity.isEmpty()) {
            throw new LaboratoryNotFoundException();
        }

        laboratoryRepository.delete(laboratoryEntity.get());
    }

    public List<LaboratoryWithCountDTO> retrieveLaboratoriesWithFilters(
            Instant initialDateStart,
            Instant initialDateEnd,
            Instant finalDateStart,
            Instant finalDateEnd,
            String searchObservation,
            String orderBy
    ) {
        Specification<LaboratoryEntity> specification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<LaboratoryEntity, PersonEntity> people = root.join("people", JoinType.LEFT);

            if (initialDateStart != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(people.get("initialDate"), initialDateStart));
            }

            if (initialDateEnd != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(people.get("initialDate"), initialDateEnd));
            }

            if (finalDateStart != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(people.get("finalDate"), finalDateStart));
            }

            if (finalDateEnd != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(people.get("finalDate"), finalDateEnd));
            }

            if (searchObservation != null) {
                predicates.add(criteriaBuilder.like(people.get("observation"), "%" + searchObservation + "%"));
            }

            query.groupBy(root.get("id"));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

        List<LaboratoryEntity> results = laboratoryRepository.findAll(specification);

        results.sort((lab1, lab2) -> {
            if ("peopleCount".equals(orderBy)) {
                return Integer.compare(lab2.getPeople().size(), lab1.getPeople().size());
            } else if ("initialDate".equals(orderBy)) {
                Instant minDate1 = lab1.getPeople().stream()
                        .map(PersonEntity::getInitialDate)
                        .filter(Objects::nonNull)
                        .min(Instant::compareTo)
                        .orElse(null);

                Instant minDate2 = lab1.getPeople().stream()
                        .map(PersonEntity::getInitialDate)
                        .filter(Objects::nonNull)
                        .min(Instant::compareTo)
                        .orElse(null);

                return ObjectUtils.compare(minDate1, minDate2);
            } else {
                return 0;
            }
        });


        return results.stream()
                .map(this::mapToLaboratoryWithCountDTO)
                .collect(Collectors.toList());
    }

    private LaboratoryWithCountDTO mapToLaboratoryWithCountDTO(LaboratoryEntity laboratoryEntity) {
        LaboratoryWithCountDTO dto = new LaboratoryWithCountDTO();
        dto.setId(laboratoryEntity.getId());
        dto.setName(laboratoryEntity.getName());
        dto.setPeopleCount(Long.valueOf(laboratoryEntity.getPeople().size()));
        return dto;
    }
}
