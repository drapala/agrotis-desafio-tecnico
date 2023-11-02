package br.com.agrotis.labapi.domain.repository;

import br.com.agrotis.labapi.domain.entity.PropertyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyInfoRepository extends JpaRepository<PropertyInfoEntity, Long> {

    PropertyInfoEntity findByName(String propertyInfoName);

}
