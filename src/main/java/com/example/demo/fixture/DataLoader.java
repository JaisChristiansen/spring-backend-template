package com.example.demo.fixture;

import com.example.demo.config.EnvironmentConfig;
import com.example.demo.enums.UserRoleName;
import com.example.demo.model.UserRole;
import com.example.demo.service.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    EnvironmentConfig environmentConfig;
    UserRoleService userRoleService;

    @Autowired
    public DataLoader(EnvironmentConfig environmentConfig, UserRoleService userRoleService) {
        this.environmentConfig = environmentConfig;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<UserRole> allRoles = userRoleService.findAll();

        if (allRoles.stream().noneMatch(userRole -> userRole.getName() == UserRoleName.DEVELOPER)) {
            UserRole developer = new UserRole();
            developer.setAccessLevel(1000);
            developer.setName(UserRoleName.DEVELOPER);
            userRoleService.persist(developer);
        }

        if (allRoles.stream().noneMatch(userRole -> userRole.getName() == UserRoleName.ADMINISTRATOR)) {
            UserRole administrator = new UserRole();
            administrator.setAccessLevel(100);
            administrator.setName(UserRoleName.ADMINISTRATOR);
            userRoleService.persist(administrator);
        }

        if (allRoles.stream().noneMatch(userRole -> userRole.getName() == UserRoleName.USER)) {
            UserRole user = new UserRole();
            user.setAccessLevel(1);
            user.setName(UserRoleName.USER);
            userRoleService.persist(user);
        }
    }
}
