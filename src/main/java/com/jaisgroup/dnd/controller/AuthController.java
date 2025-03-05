package com.jaisgroup.dnd.controller;

import com.jaisgroup.dnd.converter.UserConverter;
import com.jaisgroup.dnd.dto.LoginDto;
import com.jaisgroup.dnd.dto.UserCreationDto;
import com.jaisgroup.dnd.dto.UserDto;
import com.jaisgroup.dnd.model.User;
import com.jaisgroup.dnd.service.JwtService;
import com.jaisgroup.dnd.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    JwtService jwtService;
    UserConverter userConverter;
    UserService userService;

    @Autowired
    public AuthController(JwtService jwtService, UserConverter userConverter, UserService userService) {
        this.jwtService = jwtService;
        this.userConverter = userConverter;
        this.userService = userService;
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {
        System.out.println("Logging in!");
        User user = userService.authenticate(loginDto.getMail(), loginDto.getPassword());
        if (user != null) {
            Long session = jwtService.generateSession();
            user.setSessionToken(session);
            userService.persist(user);
            return ResponseEntity.ok()
                    .header("jwt", jwtService.generateToken(user, loginDto.getRememberMe(), session))
                    .body(convertToDto(user));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/sign-up", produces = "application/json")
    public ResponseEntity<UserDto> signUp(@RequestBody UserCreationDto userCreationDto) {
        return new ResponseEntity<>(
                convertToDto(userService.signUp(userCreationDto)),
                HttpStatus.CREATED
        );
    }

    private UserDto convertToDto(User user) {
        return userConverter.mapUserToDto(user);
    }
}
