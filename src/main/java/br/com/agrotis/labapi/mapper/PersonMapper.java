package br.com.agrotis.labapi.mapper;

import br.com.agrotis.labapi.domain.entity.PersonEntity;
import br.com.agrotis.labapi.dto.PersonDTO;
import br.com.agrotis.labapi.web.request.PersonRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PersonMapper {

    public abstract PersonDTO toDTO(PersonEntity entity);

    public abstract PersonEntity toEntity(PersonDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "laboratory.people", ignore = true)
    @Mapping(target = "propertyInfo.people", ignore = true)
    public abstract PersonEntity toEntity(PersonRequest personRequest);
}
