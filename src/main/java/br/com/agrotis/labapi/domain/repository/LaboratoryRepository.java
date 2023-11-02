package br.com.agrotis.labapi.domain.repository;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LaboratoryRepository extends JpaRepository<LaboratoryEntity, Long> {

    LaboratoryEntity findByName(String laboratoryName);

}
