package br.com.agrotis.labapi.mapper;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import br.com.agrotis.labapi.dto.LaboratoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class LaboratoryMapper {

    @Mapping(target = "people", ignore = true)
    public abstract LaboratoryDTO toDTO(LaboratoryEntity entity);

    @Mapping(target = "people", ignore = true)
    public abstract LaboratoryEntity toEntity(LaboratoryDTO dto);
}
