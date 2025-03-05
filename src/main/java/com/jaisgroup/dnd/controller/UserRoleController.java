package com.jaisgroup.dnd.controller;

import com.jaisgroup.dnd.converter.UserRoleConverter;
import com.jaisgroup.dnd.dto.UserRoleDto;
import com.jaisgroup.dnd.exception.ApiException;
import com.jaisgroup.dnd.model.UserRole;
import com.jaisgroup.dnd.service.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user-role")
public class UserRoleController {
    private final UserRoleConverter userRoleConverter;
    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleConverter userRoleConverter, UserRoleService userRoleService) {
        this.userRoleConverter = userRoleConverter;
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        try {
            List<UserRoleDto> userRoleDtos = userRoleService.findAll()
                    .stream()
                    .map(this::convertToDto)
                    .toList();
            return new ResponseEntity<>(userRoleDtos, HttpStatus.OK);
        } catch (ApiException exception) {
            return new ResponseEntity<>(exception.getStatus());
        }
    }

    @GetMapping(value = "/by-id/{id}")
    public ResponseEntity<UserRoleDto> getById(@PathVariable("id") String id) {
        Optional<UserRole> userRole = userRoleService.getById(UUID.fromString(id));
        return userRole.map(value -> new ResponseEntity<>(convertToDto(value), HttpStatus.OK)).orElse(null);
    }

    private UserRoleDto convertToDto(UserRole userRole) {
        return userRoleConverter.getModelMapper().map(userRole, UserRoleDto.class);
    }

    private UserRole convertToEntity (UserRoleDto userRoleDto) {
        UserRole userRole = userRoleConverter.getModelMapper().map(userRoleDto, UserRole.class);
        if (userRoleDto.getId() != null) {
            userRole.setId(UUID.fromString(userRoleDto.getId()));
        }
        return userRole;
    }

}
