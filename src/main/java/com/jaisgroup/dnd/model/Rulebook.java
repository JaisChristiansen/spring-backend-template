package com.jaisgroup.dnd.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Set;

@Entity(name = "rulebook")
@Table(name = "rulebook")
@Data
@EqualsAndHashCode(callSuper = true)
public class Rulebook extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "released", nullable = false)
    private Date released;
    @ManyToMany()
    @JoinTable(
            name = "rulebook_spell",
            joinColumns = @JoinColumn(name = "rulebook_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private Set<Spell> spells;
}
