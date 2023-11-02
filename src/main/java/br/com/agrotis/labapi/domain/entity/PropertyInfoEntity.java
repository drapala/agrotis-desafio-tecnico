package br.com.agrotis.labapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
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

    @JsonBackReference
    @OneToMany(mappedBy = "propertyInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonEntity> people;
}
