package com.jaisgroup.dnd.service.userRole;

import com.jaisgroup.dnd.enums.UserRoleName;
import com.jaisgroup.dnd.model.UserRole;
import com.jaisgroup.dnd.service.AbstractService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleService extends AbstractService<UserRole, UUID> {

    Optional<UserRole> getByName(UserRoleName name);


    List<UserRole> findAll();
}
