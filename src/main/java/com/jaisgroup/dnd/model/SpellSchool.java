package com.jaisgroup.dnd.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "spell_school")
@Table(name = "spell_school")
@Data
@EqualsAndHashCode(callSuper = true)
public class SpellSchool extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "color")
    private String color;
    @Column(name = "text_color")
    private String textColor;
    @OneToMany(mappedBy = "spellSchool", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Spell> spells;
}
