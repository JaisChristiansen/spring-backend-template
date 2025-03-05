package com.jaisgroup.dnd.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "damage_type")
@Table(name = "damage_type")
@Data
@EqualsAndHashCode(callSuper = true)
public class DamageType extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "svg")
    private String svg;

    @ManyToMany(mappedBy = "damageTypes")
    Set<Spell> spells;
}
