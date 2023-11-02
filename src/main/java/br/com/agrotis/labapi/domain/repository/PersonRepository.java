package br.com.agrotis.labapi.domain.repository;

import br.com.agrotis.labapi.domain.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    PersonEntity findByName(String personName);

    List<PersonEntity> findByInitialDateBetween(Instant startDate, Instant endDate);

    List<PersonEntity> findByFinalDateBetween(Instant startDate, Instant endDate);

    List<PersonEntity> findByObservation(String keyword);
}
