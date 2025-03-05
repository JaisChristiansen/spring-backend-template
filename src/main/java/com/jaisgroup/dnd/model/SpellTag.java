package com.jaisgroup.dnd.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "spell_tag")
@Table(name = "spell_tag")
@Data
@EqualsAndHashCode(callSuper = true)
public class SpellTag extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "spellTags")
    private Set<Spell> spells;
}
