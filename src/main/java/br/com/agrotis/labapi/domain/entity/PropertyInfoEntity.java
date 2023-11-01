package br.com.agrotis.labapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "property_info")
@Entity(name = "PropertyInfo")
@ToString(exclude = {"id"})
public class PropertyInfoEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_property_info", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name_property_info", nullable = false)
    private String name;

    @OneToMany(mappedBy = "propertyInfo")
    private List<PersonEntity> persons;
}
