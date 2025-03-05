package com.jaisgroup.dnd.controller;

import com.jaisgroup.dnd.converter.UserConverter;
import com.jaisgroup.dnd.dto.PasswordRequestDto;
import com.jaisgroup.dnd.dto.UserCreationDto;
import com.jaisgroup.dnd.dto.UserDto;
import com.jaisgroup.dnd.exception.ApiException;
import com.jaisgroup.dnd.exception.NotFoundException;
import com.jaisgroup.dnd.exception.UnauthorizedException;
import com.jaisgroup.dnd.model.User;
import com.jaisgroup.dnd.service.JwtService;
import com.jaisgroup.dnd.service.user.UserService;
import com.jaisgroup.dnd.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    JwtService jwtService;
    UserConverter userConverter;
    UserService userService;

    @Autowired
    public UserController(
            JwtService jwtService,
            UserConverter userConverter,
            UserService userService
    ) {
        this.jwtService = jwtService;
        this.userConverter = userConverter;
        this.userService = userService;
    }

    @GetMapping(value = "/by-session", produces = "application/json")
    public ResponseEntity<UserDto> getUser(
            @RequestHeader("Authorization") String authHeader
    ) {
        try {
            UUID userId = Optional.of(authHeader)
                    .map(s -> s.replaceAll(AuthUtil.BEARER, ""))
                    .map(jwtService::extractId)
                    .map(jsonObject -> UUID.fromString(jsonObject.get("userId").getAsString()))
                    .orElse(null);
            if (userId != null) {
                System.out.println(userId);
                Optional<User> user = userService.getById(userId);
                if (user.isPresent()) {
                    return user.map(value -> new ResponseEntity<>(convertToDto(value), HttpStatus.OK)).orElse(null);
                } else {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/by-id/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") String id) {
        Optional<User> user = userService.getById(UUID.fromString(id));
        // TODO return HTML error
        return user.map(value -> new ResponseEntity<>(convertToDto(value), HttpStatus.OK)).orElse(null);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<UserDto> createUser(
            @RequestBody UserCreationDto userCreationDto
    ) {
        return new ResponseEntity<>(
                convertToDto(userService.signUp(userCreationDto)),
                HttpStatus.CREATED
        );
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<UserDto> updateUser(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody UserDto userDto
    ) {
        if (userService.getById(UUID.fromString(userDto.getId())).isPresent()) {
            try {
                User user = getUserFromAuthHeader(authHeader);
                if (userHasEditRights(user, UUID.fromString(userDto.getId()))) {
                    return new ResponseEntity<>(
                            convertToDto(userService.persist(convertToEntity(userDto))),
                            HttpStatus.OK
                    );
                }
            } catch (ApiException exception) {
                return new ResponseEntity<>(exception.getStatus());
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/change-password", produces = "application/json")
    public ResponseEntity<UserDto> updatePassword(@RequestBody PasswordRequestDto passwordRequestDto) {
        return null;
    }

    private User getUserFromAuthHeader(String authheader) {
        UUID userId = Optional.of(authheader)
                .map(s -> s.replaceAll(AuthUtil.BEARER, ""))
                .map(jwtService::extractId)
                .map(jsonObject -> UUID.fromString(jsonObject.get("userId").getAsString()))
                .orElse(null);
        return userService.getById(userId)
                .orElseThrow(() -> new UnauthorizedException("User not found in AUTH header"));
    }

    private boolean userHasEditRights(User user, UUID userIdToEdit) {
        return user.getUserRole().getAccessLevel() >= 100 || user.getId().equals(userIdToEdit);
    }

    private UserDto convertToDto(User user) {
        return userConverter.mapUserToDto(user);
    }

    private User convertToEntity(UserDto userDto) {
        User user;
        if (userDto.getId() != null) {
            user = userService.getById(UUID.fromString(userDto.getId()))
                    .orElseThrow(() -> new NotFoundException("User not found from ID"));
        } else {
            throw new NotFoundException("No ID");
            // TODO not possible
        }
        // TODO look more into the ModelMapper, for custom mapping
        // TODO userRole and non-nullable fields
        return userConverter.mapDtoToUser(userDto, user);
    }
}
