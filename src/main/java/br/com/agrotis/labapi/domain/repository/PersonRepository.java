package br.com.agrotis.labapi.domain.repository;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import br.com.agrotis.labapi.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    PersonEntity findByName(String personName);

}
