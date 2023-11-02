package br.com.agrotis.labapi.service;

import br.com.agrotis.labapi.domain.entity.PropertyInfoEntity;
import br.com.agrotis.labapi.domain.repository.PropertyInfoRepository;
import br.com.agrotis.labapi.dto.PropertyInfoDTO;
import br.com.agrotis.labapi.exception.PropertyInfoAlreadyExistsException;
import br.com.agrotis.labapi.exception.PropertyInfoNotFoundException;
import br.com.agrotis.labapi.mapper.PropertyInfoMapper;
import br.com.agrotis.labapi.web.request.propertyInfo.PropertyInfoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyInfoService {

    private final PropertyInfoRepository propertyInfoRepository;

    private final PropertyInfoMapper propertyInfoMapper;

    public PropertyInfoDTO retrievePropertyInfoById(Long id) {
        Optional<PropertyInfoEntity> propertyInfoEntity = propertyInfoRepository.findById(id);

        if (propertyInfoEntity.isEmpty()) {
            throw new PropertyInfoNotFoundException();
        }

        PropertyInfoEntity propertyInfo = propertyInfoEntity.get();
        return propertyInfoMapper.toDTO(propertyInfo);
    }

    public List<PropertyInfoDTO> retrieveAllPropertiesInfo() {
        return propertyInfoRepository.findAll().stream().map(propertyInfoMapper::toDTO).collect(Collectors.toList());
    }

    public PropertyInfoDTO addPropertyInfo(String propertyName) {
        Optional<PropertyInfoEntity> propertyInfoEntity = Optional.ofNullable(propertyInfoRepository.findByName(propertyName));

        propertyInfoEntity.ifPresent(propertyInfo -> {
            throw new PropertyInfoAlreadyExistsException();
        });

        PropertyInfoEntity savedPropertyInfo = propertyInfoRepository.save(PropertyInfoEntity.builder().name(propertyName).build());
        return propertyInfoMapper.toDTO(savedPropertyInfo);
    }

    public PropertyInfoDTO updatePropertyInfo(PropertyInfoUpdateRequest request) {
        Optional<PropertyInfoEntity> propertyInfoEntity = propertyInfoRepository.findById(request.getId());

        if (propertyInfoEntity.isEmpty()) {
            throw new PropertyInfoNotFoundException();
        }

        propertyInfoEntity.get().setName(request.getName());

        return propertyInfoMapper.toDTO(propertyInfoRepository.save(propertyInfoEntity.get()));
    }

    public void deletePropertyInfo(Long id) {
        Optional<PropertyInfoEntity> propertyInfoEntity = propertyInfoRepository.findById(id);

        if (propertyInfoEntity.isEmpty()) {
            throw new PropertyInfoNotFoundException();
        }

        propertyInfoRepository.delete(propertyInfoEntity.get());
    }
}
