package br.com.agrotis.labapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "laboratory")
@Entity(name = "Laboratory")
@ToString(exclude = {"id"})
public class LaboratoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_laboratory", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name_laboratory", nullable = false)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "laboratory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonEntity> people;
}
