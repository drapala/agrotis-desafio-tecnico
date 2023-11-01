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
@Table(name = "laboratory")
@Entity(name = "Laboratory")
@ToString(exclude = {"id"})
public class LaboratoryEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_laboratory", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name_laboratory", nullable = false)
    private String name;

    @OneToMany(mappedBy = "laboratory", fetch = FetchType.EAGER)
    private List<PersonEntity> persons;
}
