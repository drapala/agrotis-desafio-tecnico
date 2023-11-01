package br.com.agrotis.labapi.domain.repository;

import br.com.agrotis.labapi.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
