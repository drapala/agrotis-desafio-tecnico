package br.com.agrotis.labapi.domain.repository;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryRepository extends JpaRepository<LaboratoryEntity, Long> {
}
