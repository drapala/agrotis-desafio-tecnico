package br.com.agrotis.labapi.domain.repository;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface LaboratoryRepository extends JpaRepository<LaboratoryEntity, Long>, JpaSpecificationExecutor<LaboratoryEntity> {

    LaboratoryEntity findByName(String laboratoryName);

    @Query(value = "SELECT COUNT(*) FROM person p WHERE p.id_laboratory = :laboratoryId", nativeQuery = true)
    Long countByLaboratory(@Param("laboratoryId") Long laboratoryId);
}
