package com.example.demo.model;

import com.example.demo.enums.UserRoleName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity(name = "user_role")
@Table(name = "user_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleName name;

    @Column(name = "access_level", nullable = false)
    private Integer accessLevel;

    @OneToMany(mappedBy = "userRole")
    Set<User> users;

    public UserRole() {
        super();
    }
}
