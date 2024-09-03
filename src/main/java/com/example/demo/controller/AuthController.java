package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.user.UserService;
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper;
    UserService userService;

    @Autowired
    public AuthController(JwtService jwtService, ModelMapper modelMapper, UserService userService) {
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) {
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

    @PostMapping(produces = "application/json")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto userDto) {
        System.out.println(userDto.getMail());
        return new ResponseEntity<>(
                convertToDto(userService.signUp(userDto, "abcd1234!!!!")),
                HttpStatus.CREATED
        );
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
