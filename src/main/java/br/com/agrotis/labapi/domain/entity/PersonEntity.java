package br.com.agrotis.labapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.Instant;

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

    @ManyToOne
    @JoinColumn(name = "id_property_info")
    private PropertyInfoEntity propertyInfo;

    @ManyToOne
    @JoinColumn(name = "ìd_laboratory")
    private LaboratoryEntity laboratory;

    @Column(name = "observation", nullable = false)
    private String observation;
}
