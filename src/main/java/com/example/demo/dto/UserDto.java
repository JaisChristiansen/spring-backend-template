package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String mail;
    private String firstName;
    private String lastName;
    private String callingCode;
    private String phone;
    private String description;
    private UserRoleDto userRole;
}
