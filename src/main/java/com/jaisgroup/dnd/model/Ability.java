package com.jaisgroup.dnd.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "ability")
@Table(name = "ability")
@Data
@EqualsAndHashCode(callSuper = true)
public class Ability extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;
    @Column(name = "description")
    private String description;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "text_color", nullable = false)
    private String textColor;
    @Column(name = "svg")
    private String svg;

    @ManyToMany(mappedBy = "saveAbilities")
    Set<Spell> spells;
}
