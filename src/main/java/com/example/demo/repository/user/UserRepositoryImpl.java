package com.example.demo.repository.user;

import com.example.demo.model.User;
import com.example.demo.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepositoryCustom {

}
