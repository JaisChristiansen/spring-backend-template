package com.jaisgroup.dnd.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "user_character")
@Table(name = "user_character")
@Data
@EqualsAndHashCode(callSuper = true)
public class Character extends AbstractEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "description")
    private String description;
    @Column(name = "age")
    private Integer age;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "gender")
    private String gender;
    @Column(name = "alignment")
    private String alignment;
    @Column(name = "base_armor_class", nullable = false, columnDefinition = "int default 10")
    private Integer baseArmorClass;
    @Column(name = "hit_points", nullable = false)
    private Integer hitPoints;
    @Column(name = "level", nullable = false, columnDefinition = "int default 1")
    private Integer level;

    @ManyToOne()
    @JoinColumn(name = "character_class_id")
    private CharacterClass characterClass;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany()
    @JoinTable(
            name = "character_spell",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private Set<Spell> spells;

    @ManyToOne()
    @JoinColumn(name = "race_id")
    private Race race;
}
