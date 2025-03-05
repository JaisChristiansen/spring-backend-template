package com.jaisgroup.dnd.repository.user;

import com.jaisgroup.dnd.model.User;
import com.jaisgroup.dnd.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepositoryCustom {

}
