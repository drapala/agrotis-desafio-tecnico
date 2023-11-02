package br.com.agrotis.labapi.web.request.laboratory;

import lombok.Data;

@Data
public class LaboratoryUpdateRequest {
    private Long id;

    private String name;
}
