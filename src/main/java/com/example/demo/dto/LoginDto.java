package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginDto {
    String mail;
    String password;
    Boolean rememberMe;
}
