package com.jaisgroup.dnd.service.user;

import com.jaisgroup.dnd.dto.UserCreationDto;
import com.jaisgroup.dnd.model.User;
import com.jaisgroup.dnd.service.AbstractService;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends AbstractService<User, UUID> {

    User signUp(UserCreationDto userDto);

    User changePassword(User user, String newPassword);

    Optional<User> getByMail(String mail);

    User authenticate(String mail, String password);

}
