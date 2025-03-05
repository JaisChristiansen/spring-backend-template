package com.jaisgroup.dnd.service.userRole;

import com.jaisgroup.dnd.enums.UserRoleName;
import com.jaisgroup.dnd.model.UserRole;
import com.jaisgroup.dnd.repository.userRole.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Optional<UserRole> getById(UUID uuid) {
        return userRoleRepository.findById(uuid);
    }

    @Override
    public UserRole persist(UserRole entity) {
        return userRoleRepository.save(entity);
    }

    @Override
    public void delete(UUID uuid) {
        userRoleRepository.deleteById(uuid);
    }

    @Override
    public Optional<UserRole> getByName(UserRoleName name) {
        System.out.println("Getting by name");
        return userRoleRepository.getUserRoleByName(name);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }
}
