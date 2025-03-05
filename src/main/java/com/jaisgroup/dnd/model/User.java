package com.jaisgroup.dnd.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "user")
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {
    @Column(name = "mail", nullable = false, unique = true)
    private String mail;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "calling_code")
    private String callingCode;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "active", nullable = false, columnDefinition = "tinyint(1) default 0")
    private Boolean active = false;
    @Column(name = "description")
    private String description;
    @Column(name = "session_token")
    private Long sessionToken;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Character> characters;
}
