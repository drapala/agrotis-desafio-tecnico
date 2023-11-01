package br.com.agrotis.labapi.dto;

import br.com.agrotis.labapi.domain.entity.LaboratoryEntity;
import br.com.agrotis.labapi.domain.entity.PropertyInfoEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {
    private Long id;

    private String name;

    private Instant initialDate;

    private Instant finalDate;

    private PropertyInfoEntity propertyInfo;

    private LaboratoryEntity laboratory;

    private String observation;
}
