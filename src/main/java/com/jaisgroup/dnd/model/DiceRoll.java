package com.jaisgroup.dnd.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "dice_roll")
@Table(name = "dice_roll")
@Data
@EqualsAndHashCode(callSuper = true)
public class DiceRoll extends AbstractEntity {
    @Column(name = "no_of_dice", nullable = false, columnDefinition = "default 1")
    private Integer noOfDice;
    @Column(name = "die_type", nullable = false)
    private Integer dieType;
    @Column(name = "higher_level_dice")
    private Integer higherLevelDice;
    @OneToOne(mappedBy = "diceRoll")
    private Spell spell;
}
