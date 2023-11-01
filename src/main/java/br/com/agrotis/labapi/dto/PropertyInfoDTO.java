package br.com.agrotis.labapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PropertyInfoDTO {

    private Long id;

    private String name;

    private List<PersonDTO> persons;
}
