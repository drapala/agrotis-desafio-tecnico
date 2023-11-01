package br.com.agrotis.labapi.web.request;

import lombok.Data;

import java.util.Optional;

@Data
public class LaboratoryRequest {
    private Optional<Long> id;

    private String laboratoryName;
}
