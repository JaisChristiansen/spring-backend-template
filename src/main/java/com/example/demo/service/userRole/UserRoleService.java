package com.example.demo.service.userRole;

import com.example.demo.enums.UserRoleName;
import com.example.demo.model.UserRole;
import com.example.demo.service.AbstractService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleService extends AbstractService<UserRole, UUID> {

    Optional<UserRole> getByName(UserRoleName name);

    List<UserRole> findAll();
}
