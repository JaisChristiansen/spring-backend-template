package com.jaisgroup.dnd.dto;

import lombok.Data;

@Data
public class UserRoleDto {
    private String id;
    private String name;
    private Integer accessLevel;
}
