package br.com.agrotis.labapi.web.request;

import lombok.Data;

import java.util.Optional;

@Data
public class PropertyInfoRequest {
    private Optional<Long> id;

    private String propertyInfoName;
}
