package com.jaisgroup.dnd.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "race")
@Table(name = "race")
@Data
@EqualsAndHashCode(callSuper = true)
public class Race extends AbstractEntity {
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "race", fetch = FetchType.LAZY)
    private Set<Character> characters;
}
