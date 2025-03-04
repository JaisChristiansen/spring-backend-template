package com.example.demo.converter;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRoleDto;
import com.example.demo.enums.UserRoleName;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.service.userRole.UserRoleService;
import org.modelmapper.Converter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserConverter extends AbstractConverter {

    UserRoleService userRoleService;

    @Autowired
    public UserConverter(UserRoleService userRoleService) {

        TypeMap<UserRoleDto, UserRole> userRoleMapper = getModelMapper().createTypeMap(UserRoleDto.class, UserRole.class);

        Converter<UserRoleDto, UserRole> userRoleConverter = mappingContext -> {
            if (mappingContext.getSource().getId() != null) {
                return userRoleService.getById(UUID.fromString(mappingContext.getSource().getId()))
                        .orElse(userRoleService.getByName(UserRoleName.USER)
                                .orElse(null));
            } else {
                return userRoleService.getByName(UserRoleName.USER).orElse(null);
            }
        };

        getModelMapper().addConverter(userRoleConverter);
        userRoleMapper.setConverter(userRoleConverter);
//        userMapper.addMappings(mapper -> mapper.using(userRoleConverter).map(UserDto::getUserRole, User::setUserRole));

        this.userRoleService = userRoleService;
    }


    public UserDto mapUserToDto(User user) {
        return getModelMapper().map(user, UserDto.class);
    }

    public User mapDtoToUser(UserDto userDto, User user) {
        getModelMapper().map(userDto, user);
        return user;
    }


}
