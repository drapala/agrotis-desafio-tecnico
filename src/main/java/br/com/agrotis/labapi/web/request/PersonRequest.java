package br.com.agrotis.labapi.web.request;

import br.com.agrotis.labapi.web.request.laboratory.LaboratoryRequest;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoRequest;
import lombok.Data;

import java.time.Instant;

@Data
public class PersonRequest {
    private String name;

    private Instant initialDate;

    private Instant finalDate;

    private PropertyInfoRequest propertyInfo;

    private LaboratoryRequest laboratory;

    private String observation;
}
