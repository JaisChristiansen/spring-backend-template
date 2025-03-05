package com.jaisgroup.dnd.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "area_of_effect")
@Table(name = "area_of_effect")
@Data
@EqualsAndHashCode(callSuper = true)
public class AreaOfEffect extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "svg", nullable = false)
    private String svg;

    @OneToMany(mappedBy = "areaOfEffect")
    private Set<Spell> spells;

}
