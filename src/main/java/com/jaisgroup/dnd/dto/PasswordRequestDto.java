package com.jaisgroup.dnd.dto;

import lombok.Data;

@Data
public class PasswordRequestDto {
    private String token;
    private String password;
}
