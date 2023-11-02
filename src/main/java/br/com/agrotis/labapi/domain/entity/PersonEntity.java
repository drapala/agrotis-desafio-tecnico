package br.com.agrotis.labapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
@Entity(name = "Person")
@ToString(exclude = {"id"})
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_person", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name_person", nullable = false)
    private String name;

    @Column(name = "initial_date", nullable = false)
    private Instant initialDate;

    @Column(name = "final_date", nullable = false)
    private Instant finalDate;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_property_info")
    private PropertyInfoEntity propertyInfo;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_laboratory")
    private LaboratoryEntity laboratory;

    @Column(name = "observation")
    private String observation;
}
