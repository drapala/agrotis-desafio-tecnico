package br.com.agrotis.labapi.web.request.propertyInfo;

import lombok.Data;

@Data
public class PropertyInfoUpdateRequest {
    private Long id;

    private String name;
}
