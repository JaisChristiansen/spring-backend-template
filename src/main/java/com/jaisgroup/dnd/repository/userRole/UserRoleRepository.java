package com.jaisgroup.dnd.repository.userRole;

import com.jaisgroup.dnd.enums.UserRoleName;
import com.jaisgroup.dnd.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    @Query(
            value = "SELECT ur FROM user_role ur WHERE ur.name = ?1"
    )
    Optional<UserRole> getUserRoleByName(UserRoleName name);
}
