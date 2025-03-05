package com.jaisgroup.dnd.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCreationDto extends UserDto{
    String password;
}
