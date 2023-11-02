package br.com.agrotis.labapi.domain.listener;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class LaboratoryEntityListener {
    
    @PrePersist
    @PreUpdate
    public void beforeSave(LaboratoryEntity laboratory) {
        laboratory.setName(laboratory.getName().toUpperCase());
    }
}
