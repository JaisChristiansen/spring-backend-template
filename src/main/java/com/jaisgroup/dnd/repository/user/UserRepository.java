package com.jaisgroup.dnd.repository.user;

import com.jaisgroup.dnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, UserRepositoryCustom {

    @Query(
            value = "SELECT u from user u where u.mail = ?1"
    )
    Optional<User> getUserByMail(String mail);
}
