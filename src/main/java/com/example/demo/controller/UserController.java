package com.example.demo.controller;

import com.example.demo.dto.PasswordRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.user.UserService;
import com.example.demo.util.AuthUtil;
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper;
    UserService userService;

    @Autowired
    public UserController(JwtService jwtService, ModelMapper modelMapper, UserService userService) {
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
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
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(
                convertToDto(userService.signUp(userDto, "abcd1234!!!!")),
                HttpStatus.CREATED
        );
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return null;
    }

    @PutMapping(value = "/change-password", produces = "application/json")
    public ResponseEntity<UserDto> updatePassword(@RequestBody PasswordRequestDto passwordRequestDto) {
        return null;
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private User convertToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (userDto.getId() != null) {
            user.setId(UUID.fromString(userDto.getId()));
        }
        // TODO userRole
        return user;
    }
}
