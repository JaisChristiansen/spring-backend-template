package com.jaisgroup.dnd.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "spell")
@Table(name = "spell")
@Data
@EqualsAndHashCode(callSuper = true)
public class Spell extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String text;
    @Column(name = "higher_levels")
    private String higherLevels;
    @Column(name = "concentration", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean concentration;
    @Column(name = "ritual", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean ritual;
    @Column(name = "range")
    private Integer range;
    @Column(name = "area")
    private Integer area;
    @Column(name = "casting_time")
    private String castingTime;
    @Column(name = "duration")
    private String duration;
    @Column(name = "components_verbal", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean componentsVerbal;
    @Column(name = "components_somatic", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean componentsSomatic;
    @Column(name = "components_material", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean componentsMaterial;
    @Column(name = "materials")
    private String materials;
    @Column(name = "damage_description")
    private String damageDescription;

    @ManyToOne()
    @JoinColumn(name = "spell_level_id", nullable = false)
    private SpellLevel spellLevel;
    @ManyToOne()
    @JoinColumn(name = "spell_school_id", nullable = false)
    private SpellSchool spellSchool;
    @OneToOne(optional = false)
    @JoinColumn(name = "dice_roll_id", nullable = false)
    private DiceRoll diceRoll;
    @ManyToMany(mappedBy = "spells")
    Set<CharacterClass> characterClasses;
    @ManyToMany(mappedBy = "spells")
    Set<Rulebook> rulebooks;
    @ManyToMany()
    @JoinTable(
            name = "spell_condition",
            joinColumns = @JoinColumn(name = "spell_id"),
            inverseJoinColumns = @JoinColumn(name = "condition_id")
    )
    private Set<Condition> conditions;
    @ManyToMany()
    @JoinTable(
            name = "spell_damage_type",
            joinColumns = @JoinColumn(name = "spell_id"),
            inverseJoinColumns = @JoinColumn(name = "damage_type_id")
    )
    private Set<DamageType> damageTypes;
    // TODO Save(s) required (Ability)
    // TODO Area Type
    // TODO Spell Tags
}
