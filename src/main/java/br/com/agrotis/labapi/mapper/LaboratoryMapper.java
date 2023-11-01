package br.com.agrotis.labapi.mapper;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import br.com.agrotis.labapi.dto.LaboratoryDTO;
import br.com.agrotis.labapi.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class LaboratoryMapper {

    @Mapping(target = "persons", ignore = true)
    public abstract LaboratoryDTO toDTO(LaboratoryEntity entity);

    @Mapping(target = "persons", ignore = true)
    public abstract LaboratoryEntity toEntity(LaboratoryDTO dto);
}
