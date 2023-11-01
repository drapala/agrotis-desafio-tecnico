package br.com.agrotis.labapi.mapper;

import br.com.agrotis.labapi.domain.entity.PropertyInfoEntity;
import br.com.agrotis.labapi.dto.PropertyInfoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PropertyInfoMapper {

    @Mapping(target = "persons", ignore = true)
    public abstract PropertyInfoDTO toDTO(PropertyInfoEntity entity);

    @Mapping(target = "persons", ignore = true)
    public abstract PropertyInfoEntity toEntity(PropertyInfoDTO dto);
}
