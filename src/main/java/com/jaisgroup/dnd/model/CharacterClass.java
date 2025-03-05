package com.jaisgroup.dnd.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "character_class")
@Table(name = "character_class")
@Data
@EqualsAndHashCode(callSuper = true)
public class CharacterClass extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToMany()
    @JoinTable(
            name = "character_class_spell",
            joinColumns = @JoinColumn(name = "character_class_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    Set<Spell> spells;
    // TODO add more
}
