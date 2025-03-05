package com.jaisgroup.dnd.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "spell_level")
@Table(name = "spell_level")
@Data
@EqualsAndHashCode(callSuper = true)
public class SpellLevel extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "level", nullable = false)
    private Integer level;

    @OneToMany(mappedBy = "spellLevel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Spell> spells;
}
