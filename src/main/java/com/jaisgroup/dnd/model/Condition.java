package com.jaisgroup.dnd.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "condition_effect")
@Table(name = "condition_effect")
@Data
@EqualsAndHashCode(callSuper = true)
public class Condition extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "svg")
    private String svg;

    @ManyToMany(mappedBy = "conditions")
    private Set<Spell> spells;
}
